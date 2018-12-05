package com.amitchell05.cheesemvc.controllers;

import com.amitchell05.cheesemvc.models.Category;
import com.amitchell05.cheesemvc.models.Cheese;
import com.amitchell05.cheesemvc.models.data.CategoryDao;
import com.amitchell05.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value ="add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors,
                                       @RequestParam int categoryId,
                                       Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }

        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        Cheese currentCheese = cheeseDao.findOne(cheeseId);
        model.addAttribute("cheese", currentCheese);
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Edit Cheese " + currentCheese.getName() + " (" + cheeseId + ")");
        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int cheeseId, String name, String description, Category category, String rating, @ModelAttribute @Valid Cheese currentCheese,
                                  Errors errors, Model model) {

        currentCheese = cheeseDao.findOne(cheeseId);

        if (errors.hasErrors()) {
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("title", "Edit Cheese " + currentCheese.getName() + " (" + cheeseId + ")" );
            return "cheese/edit";
        }

        currentCheese.setName(name);
        currentCheese.setDescription(description);
        currentCheese.setCategory(category);
        currentCheese.setRating(rating);

        return "redirect:/cheese";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }
        return "redirect:";
    }
}