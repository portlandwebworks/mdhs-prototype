package com.portlandwebworks.mdhs;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nick
 */
@Controller
@RequestMapping("/templates")
public class TemplatesController {

	private static final Logger log = LoggerFactory.getLogger(TemplatesController.class);

	@RequestMapping("/{page}")
	public String direct(@PathVariable("page") String page) {
		final String finalPage = "/" + page;
		log.trace("Trying to render page: {}", finalPage);
		return finalPage;
	}

	@RequestMapping("/{dir}/{page}")
	public String direct(@PathVariable("dir") String dir, @PathVariable("page") String page) {
		return finalPage(page, dir);
	}

	@RequestMapping("/{dir}/{dir2}/{page}")
	public String direct(@PathVariable("dir") String dir, @PathVariable("dir2") String dir2, @PathVariable("page") String page) {
		return finalPage(page, dir, dir2);
	}

	@RequestMapping("/{dir}/{dir2}/{dir3}/{page}")
	public String direct(@PathVariable("dir") String dir, @PathVariable("dir2") String dir2, @PathVariable("dir3") String dir3, @PathVariable("page") String page) {
		return finalPage(page, dir, dir2, dir3);
	}

	private String finalPage(String page, String... dirs) {
		final String finalPage = Arrays.asList(dirs).stream()
				.collect(Collectors.joining("/", "/", "/" + page));
		log.trace("Trying to render page: {}", finalPage);
		return finalPage;
	}

}
