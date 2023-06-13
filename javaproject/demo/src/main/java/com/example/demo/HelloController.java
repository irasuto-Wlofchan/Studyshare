package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.repositories.PersonRepository;
import jakarta.transaction.Transactional;

  import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class HelloController {
    
    @Autowired
    PersonRepository repository;
    
   // @RequestMapping(value="/",method=RequestMethod.GET)
   @RequestMapping("/")
  public ModelAndView index(
      @ModelAttribute("formModel") Person Person,ModelAndView mav) {
    mav.setViewName("index");
    mav.addObject("title", "Hello page");
    mav.addObject("msg","this is JPA sample data.");
    List<Person> list = repository.findAll();
    mav.addObject("data",list);
    return mav;
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  @Transactional
  public ModelAndView form(
      @ModelAttribute("formModel") Person Person, 
      ModelAndView mav) {
    repository.saveAndFlush(Person);
    return new ModelAndView("redirect:/");
  }
  }
    

