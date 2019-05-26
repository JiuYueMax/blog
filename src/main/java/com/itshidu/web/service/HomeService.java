package com.itshidu.web.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.ModelAndView;

public interface HomeService {

	void follows(Integer page,ModelAndView mv);

	void fans(Integer page, ModelAndView mv);

	void notifies(Integer page, ModelAndView mv);

	void favors(Integer page, ModelAndView mv);
}
