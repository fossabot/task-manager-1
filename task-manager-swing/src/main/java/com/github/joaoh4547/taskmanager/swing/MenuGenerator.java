package com.github.joaoh4547.taskmanager.swing;

import java.awt.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.*;

import org.apache.commons.lang3.StringUtils;

import com.github.joaoh4547.taskmanager.utils.ReflectionUtils;

/**
 * This class is responsible for generating a JMenuBar containing menu items
 * based on the pages annotated with @Page.
 */
public class MenuGenerator {

  /**
   * Generates a JMenuBar containing menu items based on the pages annotated
   * with @Page.
   *
   * @return a JMenuBar populated with menus and menu items corresponding to the
   *         pages' details
   */
  public static JMenuBar generateMenu(JFrame parent) {
    Collection<Class<? extends AbstractSwingPage>> classes = ReflectionUtils
        .getSubclasses(AbstractSwingPage.class);

    Collection<Page> pages = classes.stream()
        .map(c -> c.getAnnotation(Page.class)).toList();

    Set<String> pageGroups = pages.stream().map(Page::pageGroup)
        .collect(Collectors.toSet());

    JMenuBar menuBar = new JMenuBar();

    for (String pageGroup : pageGroups) {
      JMenu menu = new JMenu(pageGroup);
      menuBar.add(menu);
      for (Class<? extends AbstractSwingPage> clazz : classes) {
        Page page = clazz.getAnnotation(Page.class);
        if (page.pageGroup().equals(pageGroup)) {
          menu.add(createMenuItem(clazz, page, parent));
        }
      }
    }

    return menuBar;

  }

  private static JMenuItem createMenuItem(Class<? extends AbstractSwingPage> clazz,
                                          Page page, JFrame parent) {
    JMenuItem menuItem = new JMenuItem(page.name());
    menuItem.setToolTipText(page.description());
    menuItem.addActionListener(e -> {
      AbstractSwingPage p = ReflectionUtils.newInstance(clazz);
      p.setVisible(true);
      p.setLocationRelativeTo(parent);
      p.setAlwaysOnTop(true);
      p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      p.setSize(new Dimension(600, 600));
      // p.setP
      String title = null;
      if (StringUtils.isEmpty(page.description())) {
        title = page.name();
      } else {
        title = String.format("%s - %s", page.name(), page.description());
      }
      p.setTitle(title);
    });
    return menuItem;
  }
}
