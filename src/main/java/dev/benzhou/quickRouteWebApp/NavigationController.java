package dev.benzhou.quickRouteWebApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.benzhou.ds.Path;
import dev.benzhou.routeFinder.FindRoute;

@Controller
public class NavigationController {

	@RequestMapping(value = { "quickroute", "/quickroute", "" })
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("index");

		return mv;
	}

	@RequestMapping("/quickroute/findroute")
	public ModelAndView findRoute(@RequestParam("location[]") String[] locations,
			@RequestParam("city[]") String[] cities, @RequestParam("country[]") String[] countries,
			@RequestParam("startLocation") String startLocation, @RequestParam("startCity") String startCity,
			@RequestParam("startCountry") String startCountry, @RequestParam("TransportMethod") String transportMethod,
			@RequestParam("WeightMethod") String weightMethod) {
		ModelAndView mv = new ModelAndView();

		List<String> places = new ArrayList<>();

		for (int i = 0; i < locations.length; i++) {
			if (locations[i].isBlank()) {
				places.add(WordUtils.capitalizeFully(cities[i].trim() + ", " + countries[i].trim()));
			} else {
				places.add(WordUtils
						.capitalizeFully(locations[i].trim() + ", " + cities[i].trim() + ", " + countries[i].trim()));
			}
		}

		String startPlace;
		if (startLocation.isBlank() && startCity.isBlank() && startCountry.isBlank()) {
			startPlace = "";
		} else if (startLocation.isBlank()) {
			startPlace = WordUtils.capitalizeFully(startCity.trim() + ", " + startCountry.trim());
		} else {
			startPlace = WordUtils
					.capitalizeFully(startLocation.trim() + ", " + startCity.trim() + ", " + startCountry.trim());
		}

		try {
			FindRoute route = new FindRoute(places, startPlace, transportMethod, weightMethod);
			Path path = route.start();

			Map<String, String> func = new HashMap<String, String>();
			mv.addObject("func", func);
			func.put("results", "processResults()");

			mv.addObject("path", path.toArray());
			mv.addObject("pathCosts", path.getCostArray(weightMethod));
			mv.addObject("totalCost", "Total " + WordUtils.capitalizeFully(weightMethod) + ": "
					+ path.getHumanReadableCost(weightMethod));
			mv.addObject("transportMethod", transportMethod.toLowerCase());
			mv.setViewName("results");
		} catch (IOException e) {
			Map<String, String> func = new HashMap<String, String>();
			mv.addObject("func", func);
			if (e.getMessage().equals("Invalid Start Location")) {
				func.put("error", "startError()");
				mv.setViewName("index");
			} else if (e.getMessage().equals("Invalid Location")) {
				func.put("error", "locationsError()");
				mv.setViewName("index");
			}
		} catch (Exception e) {
			Map<String, String> func = new HashMap<String, String>();
			func.put("error", "noValidRouteError()");
			mv.addObject("func", func);
			mv.setViewName("results");
		}

		return mv;
	}

}
