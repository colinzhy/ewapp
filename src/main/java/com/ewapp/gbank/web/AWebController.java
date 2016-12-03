package com.ewapp.gbank.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.web.servlet.ModelAndView;

import com.ewapp.gbank.web.dto.ABaseDTO;
import com.ewapp.gbank.web.dto.Action;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

public abstract class AWebController<T extends ABaseDTO> {

  protected static final String ALERT_MESSAGE = "alertMessage";

  private static final String REDIRECT = "redirect:";

  private static final String FORWARD = "forward:";

  protected static final String LABEL_SUCCESS_CREATED = "general.success.created";

  protected static final String LABEL_SUCCESS_UPDATED = "general.success.updated";

  protected static final String LABEL_SUCCESS_DELETED = "general.success.deleted";

  protected static final String LABEL_FAILED_CREATE = "general.failed.create";

  protected static final String LABEL_FAILED_UPDATE = "general.failed.update";

  protected static final String LABEL_FAILED_DELETE = "general.failed.delete";

  protected static final String ACTION_DELETE = "delete";
  protected static final String ACTION_EDIT = "edit";
  protected static final String ACTION_RESTORE = "restore";
  protected static final String ACTION_VIEW = "view";
  protected static final String ACTION_RESET = "reset";

  protected static final String CSS_STYLE_DELETE = "bg-red";
  protected static final String CSS_STYLE_EDIT = "bg-blue";
  protected static final String CSS_STYLE_RESTORE = "bg-green";
  protected static final String CSS_STYLE_VIEW = "bg-blue";
  protected static final String CSS_STYLE_RESET = "bg-blue";

  protected static final String ICON_DELETE = "fa fa-times";
  protected static final String ICON_EDIT = "fa fa-edit";
  protected static final String ICON_RESTORE = "fa fa-undo";
  protected static final String ICON_VIEW = "fa fa-shopping-cart";
  protected static final String ICON_RESET = "fa fa-upload";

  protected static final String ALT_TEXT_SUBFIX = ".action.tooltip.delete";

  @Autowired
  private MessageSource messageSource;

  private MessageSourceAccessor messageSourceAccessor;

  /**
   * Initialization of Controllers.
   */
  @PostConstruct
  private void init() {
    messageSourceAccessor = new MessageSourceAccessor(messageSource);
  }

  /**
   * Get message for given code and parameters.
   * 
   * @param code message label
   * @param parameters parameters set into the message
   * @return translated message
   */
  protected String getMessage(String code, String... parameters) {
    return messageSourceAccessor.getMessage(code, parameters);
  }

  /**
   * redirect to page tag, this tag must be started with "/".
   * 
   * @param pageName page tag
   * @return redirect ModelAndView object of the page
   */
  protected static ModelAndView redirectTo(String pageName) {
    return createModelAndView(REDIRECT + pageName);
  }

  /**
   * forward to page tag, this tag must be started with "/".
   * 
   * @param pageName page tag
   * @return forward ModelAndView object of the page
   */
  protected static ModelAndView forwardTo(String pageName) {
    return createModelAndView(FORWARD + pageName);
  }

  /**
   * Create model and view for the given page.
   * 
   * @param pageName page name
   * @return ModelAndView object of this page
   */
  protected static ModelAndView createModelAndView(String pageName) {
    ModelAndView modelAndView = new ModelAndView(pageName);
    return modelAndView;
  }

  protected ModelAndView backToMainPage() {
    return redirectTo(getBase());
  }

  public ModelAndView processUnexpectException(Exception ex, HttpServletRequest request) {
    ModelAndView modelAndView = new ModelAndView("error");
    modelAndView.addObject("exception", ex);
    modelAndView.addObject("url", request.getRequestURI());
    return modelAndView;
  }

  /**
   * Change DatatablesCriterias to Pageable
   * 
   * @param criterias
   * @return Pageable
   */
  protected Pageable toPageable(DatatablesCriterias criterias) {
    Sort sort = null;
    // Handle sorting if provided
    if (criterias.hasOneSortedColumn()) {
      List<Sort.Order> orders = new ArrayList<Sort.Order>();
      for (ColumnDef columnDef : criterias.getSortingColumnDefs()) {
        orders.add(new Order(Direction.valueOf(columnDef.getSortDirection().name()), columnDef.getName()));
      }
      sort = new Sort(orders);
    }
    int start = criterias.getStart() > 0 ? criterias.getStart() : 1;
    int size = criterias.getLength();
    return new PageRequest(start / size, size, sort);
  }

  /**
   * Return main page tag, this tag must be started with "/".
   * 
   * @return url for main page of current page
   */
  protected abstract String getBase();

  /**
   * create edit action for list page.
   * 
   * @param base base URL for the action
   * @param altText action placeholder
   * @return edit action object
   */
  protected Action createEditAction(String base, String altText) {
    return new Action(base, ACTION_EDIT, CSS_STYLE_EDIT, ICON_EDIT, altText);
  }

  /**
   * create edit action with default altText (base + ALT_TEXT_SUBFIX) for list page.
   * 
   * @param base base URL for the action
   * @return edit action object
   */
  protected Action createEditAction(String base) {
    return createEditAction(base, base + ALT_TEXT_SUBFIX);
  }

  /**
   * create delete action for list page.
   * 
   * @param base base URL for the action
   * @param altText action placeholder
   * @return delete action object
   */
  protected Action createDeleteAction(String base, String altText) {
    return new Action(base, ACTION_DELETE, CSS_STYLE_DELETE, ICON_DELETE, altText);
  }

  /**
   * create delete action with default altText (base + ALT_TEXT_SUBFIX) for list page.
   * 
   * @param base base URL for the action
   * @return delete action object
   */
  protected Action createDeleteAction(String base) {
    return createDeleteAction(base, base + ALT_TEXT_SUBFIX);
  }

  /**
   * create view action for list page.
   * 
   * @param base base URL for the action
   * @param altText action placeholder
   * @return view action object
   */
  protected Action createViewAction(String base, String altText) {
    return new Action(base, ACTION_VIEW, CSS_STYLE_VIEW, ICON_VIEW, altText);
  }

  /**
   * create view action with default altText (base + ALT_TEXT_SUBFIX) for list page.
   * 
   * @param base base URL for the action
   * @return view action object
   */
  protected Action createViewAction(String base) {
    return createViewAction(base, base + ALT_TEXT_SUBFIX);
  }

  /**
   * create restore action for list page.
   * 
   * @param base base URL for the action
   * @param altText action placeholder
   * @return restore action object
   */
  protected Action createRestoreAction(String base, String altText) {
    return new Action(base, ACTION_RESTORE, CSS_STYLE_RESTORE, ICON_RESTORE, altText);
  }

  /**
   * create restore action with default altText (base + ALT_TEXT_SUBFIX) for list page.
   * 
   * @param base base URL for the action
   * @return restore action object
   */
  protected Action createRestoreAction(String base) {
    return createRestoreAction(base, base + ALT_TEXT_SUBFIX);
  }

  /**
   * create reset action for list page.
   * 
   * @param base base URL for the action
   * @param altText action placeholder
   * @return reset action object
   */
  protected Action createResetAction(String base, String altText) {
    return new Action(base, ACTION_RESET, CSS_STYLE_RESET, ICON_RESET, altText);
  }

  /**
   * create reset action with default altText (base + ALT_TEXT_SUBFIX) for list page.
   * 
   * @param base base URL for the action
   * @return reset action object
   */
  protected Action createResetAction(String base) {
    return createResetAction(base, base + ALT_TEXT_SUBFIX);
  }

  protected void initializeAction(Collection<T> objects, Collection<Action> actions) {
    if (objects == null || objects.isEmpty() || actions == null || actions.isEmpty()) {
      return;
    }
    objects.forEach(//
        object -> object.addActions(//
            actions.stream().map(//
                action -> {
                  action.setObjectId(object.getId());
                  return action;
                }//
            ).collect(Collectors.toList())//
        )//
    );
  }

  protected abstract Collection<Action> getActions(HttpServletRequest request);

}
