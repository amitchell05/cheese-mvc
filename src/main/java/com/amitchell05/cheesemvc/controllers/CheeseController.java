package com.amitchell05.cheesemvc.controllers;

import com.amitchell05.cheesemvc.models.Cheese;
import com.amitchell05.cheesemvc.models.CheeseData;
import com.amitchell05.cheesemvc.models.CheeseType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
//        // Same as this line of code:
//        model.addAttribute("cheese", new Cheese());
        model.addAttribute("cheeseTypes", CheeseType.values());

        return "cheese/add";
    }

    @RequestMapping(value ="add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }

        CheeseData.add(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {
        model.addAttribute("cheese", CheeseData.getById(cheeseId));
        model.addAttribute("cheeseTypes", CheeseType.values());
        model.addAttribute("title", "Edit");
        return "cheese/edit";
    }

    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int cheeseId, String name, String description, CheeseType type, @ModelAttribute @Valid Cheese currentCheese,
                                  Errors errors, Model model) {

        if (errors.hasErrors()) {
//            model.addAttribute("cheese", CheeseData.getById(cheeseId));
            model.addAttribute("cheeseTypes", CheeseType.values());
            model.addAttribute("title", "Edit");
            return "cheese/edit";
        }

        currentCheese = CheeseData.getById(cheeseId);
        currentCheese.setName(name);
        currentCheese.setDescription(description);
        currentCheese.setType(type);
        return "redirect:/cheese";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", CheeseData.getAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {
        for (int cheeseId : cheeseIds) {
            CheeseData.remove(cheeseId);
        }
        return "redirect:";
    }
}