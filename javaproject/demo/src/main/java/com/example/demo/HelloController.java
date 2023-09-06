package com.example.demo;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.repositories.PersonRepository;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;



@Controller
public class HelloController {
    
    @Autowired
    PersonRepository repository;

    @Autowired
    PersonDAOPersonimpl dao;
    
   @RequestMapping(value="/",method=RequestMethod.GET)
   //@RequestMapping("/")
  public ModelAndView index(
      @ModelAttribute("formModel") Person Person,ModelAndView mav) {
    mav.setViewName("index");
    mav.addObject("title", "Hello page");
    mav.addObject("msg","this is JPA sample data.");
    List<Person> list = repository.findAll();
    mav.addObject("data",list);
    return mav;
  }
  //find画面の処理
  @RequestMapping(value = "/find",method = RequestMethod.GET)
  public ModelAndView index(ModelAndView mav){
    mav.setViewName("find");
    mav.addObject("msg","Personのサのサンプルです");
    Iterable<Person> list = dao.getAll();
    //テスト用list
    // List<Person> list = repository.findAll();
    mav.addObject("data",list);
    return mav;
  }

@RequestMapping(value = "/", method = RequestMethod.POST)
@Transactional
public ModelAndView form(
    @ModelAttribute("formModel") @Validated Person person, 
    BindingResult result,
    ModelAndView mav) {
  ModelAndView res = null;
  System.out.println(result.getFieldErrors());
  if (!result.hasErrors()){
    repository.saveAndFlush(person);
    res = new ModelAndView("redirect:/");
  } else {
    mav.setViewName("index");
    mav.addObject("title", "Hello page");
    mav.addObject("msg","sorry, error is occurred...");
    Iterable<Person> list = repository.findAll();
    mav.addObject("datalist",list);
    res = mav;
  }
  return res;
}
  //edit画面処理
  @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
  public ModelAndView edit(@ModelAttribute Person person,
    @PathVariable int id,ModelAndView mav){
      mav.setViewName("edit");
      mav.addObject("title","edit Person");
      Optional <Person> data = repository.findById((long)id);
      mav.addObject("formModel",data.get());                   
      return mav;
    }


    
  @RequestMapping(value="/edit",method = RequestMethod.POST)
    @Transactional
    public ModelAndView update(@ModelAttribute Person Person,
     ModelAndView mav){
      repository.saveAndFlush(Person);
      return new ModelAndView("redirect:/");
     }


  @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
  public ModelAndView delete(@PathVariable int id,ModelAndView mav){
    mav.setViewName("delete");
    mav.addObject("title","Delete Person");                 
    mav.addObject("msg", "Can I delete this record?");
    Optional<Person> data = repository.findById((long) id);
    mav.addObject("formModel",data.get());
    return mav;
  }

  @RequestMapping(value = "/delete",method = RequestMethod.POST)
  @Transactional
  public ModelAndView remove(@RequestParam long id, ModelAndView mav){
    repository.deleteById(id);
    return new ModelAndView("redirect:/");
  }

@PostConstruct
public void init(){

  Person p1 = new Person();
  //一つ目のダミーデータ
  p1.setName("taro");
  p1.setAge(39);
  p1.setMail("taro@yamada");
  repository.saveAndFlush(p1);

  //二つ目のダミーデータ
  Person p2 = new Person();
  p2.setName("hanako");
  p2.setAge(28);
  p2.setMail("hanako@flower");
  repository.saveAndFlush(p2);

  //三つ目のダミーデータ

  Person p3 = new Person();
  p3.setName("sachiko");
  p3.setAge(17);
  p3.setMail("sachico@happy");
  repository.saveAndFlush(p3);
}

}
    

