package com.ewapp.gbank.web;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ewapp.gbank.service.ICrudService;
import com.ewapp.gbank.web.dto.ABaseDTO;
import com.ewapp.gbank.web.dto.AlertMessageDto;

public abstract class ACrudController<T extends ABaseDTO> extends AWebController<T> {

  @Inject
  private ICrudService<T> crudService;

  @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
  public ModelAndView listPage() {
    ModelAndView modelAndView = new ModelAndView(getBase() + "/list");
    return modelAndView;
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public @ResponseBody Page<T> listTableData(HttpServletRequest request) {
    Page<T> pageResult = crudService.findPage(null, null);
    initializeAction(pageResult.getContent(), getActions(request));
    return pageResult;
  }

  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView modelAndView = new ModelAndView(getBase() + "/form");
    addExtraFormAttributeForCreatePage(modelAndView);
    return modelAndView;
  }

  /**
   * add extra form attribute for create page, such as a empty object, etc.
   * 
   * @param modelAndView
   */
  protected abstract void addExtraFormAttributeForCreatePage(ModelAndView modelAndView);

  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public ModelAndView createObject(@ModelAttribute("object") T object, BindingResult result,
      RedirectAttributes redirectAttributes) {
    // TODO: handle validation exception
    crudService.create(object);
    redirectAttributes.addFlashAttribute(ALERT_MESSAGE, AlertMessageDto.success(getMessage(LABEL_SUCCESS_CREATED)));
    return backToMainPage();
  }

  /**
   * enter into the edit form page of the T.
   * 
   * @return modelAndView
   */
  @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
  public ModelAndView edit(@PathVariable("id") String id) {
    ModelAndView modelAndView = new ModelAndView(getBase() + "/form");
    modelAndView.addObject("object", crudService.findById(id));
    addExtraFormAttributeForEditPage(modelAndView);
    return modelAndView;
  }

  protected abstract void addExtraFormAttributeForEditPage(ModelAndView modelAndView);

  /**
   * update the object.
   * 
   * @return modelAndView
   */
  @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
  public ModelAndView updateObject(@ModelAttribute("object") T object, BindingResult result,
      @PathVariable("id") String id, RedirectAttributes redirectAttributes) {
    crudService.update(object);
    redirectAttributes.addFlashAttribute(ALERT_MESSAGE, AlertMessageDto.success(getMessage(LABEL_SUCCESS_UPDATED)));
    return backToMainPage();
  }

  /**
   * delete object.
   * 
   * @return modelAndView
   */
  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  public ModelAndView delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
    crudService.deleteById(id);
    redirectAttributes.addFlashAttribute(ALERT_MESSAGE, AlertMessageDto.success(getMessage(LABEL_SUCCESS_DELETED)));
    return backToMainPage();
  }

  protected abstract void addExtraFormAttributeForViewPage(ModelAndView modelAndView);

  /**
   * enter into the view page of the object.
   * 
   * @return modelAndView
   */
  @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
  public ModelAndView view(@PathVariable("id") String id) {
    ModelAndView modelAndView = new ModelAndView(getBase() + "/view");
    modelAndView.addObject("object", crudService.findById(id));
    addExtraFormAttributeForViewPage(modelAndView);
    return modelAndView;
  }

}
