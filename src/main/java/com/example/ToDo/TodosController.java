package com.example.ToDo;

import java.util.ArrayList;

import com.example.ToDo.models.Todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodosController {
    
    ArrayList<Todo> todos;

    public TodosController(){
        setTodos(new ArrayList<Todo>());

        createDemoData();
    }

    private void createDemoData(){
        getTodos().add(new Todo("Müll rausbringen!", "Amelie"));
        getTodos().add(new Todo("Küche aufräumen", "Jordan"));
    }


    @GetMapping("/todos")
    public String todos(@RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        model.addAttribute("activePage", "todos");
        model.addAttribute("todos", getTodos());
        return "index.html";
    }



    // required true ist nur, dass eine id als parameter mit übergeben werden muss
    // default value, wird direkt als int gespeichert
    @RequestMapping("/deltodo")
    public String deltodo(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        getTodos().remove(id);
        return "redirect:/todos";
    }




    @RequestMapping("/changetodo")
    public String changetodo(@RequestParam(name="id", required = true, defaultValue = "null") int id, @RequestParam(name="activePage", required = false, defaultValue = "changetodo") String activePage, Model model){
        // Todo zur Bearbeitung laden
        model.addAttribute("todo", getTodos().get(id));
        model.addAttribute("todoid", id);
        model.addAttribute("activePage", "todoUpdate");
        return "index.html";
    }
    
    @RequestMapping("/updatetodo")
    public String updatetodo(@RequestParam(name="todoId", required = true, defaultValue = "null") int todoId, @RequestParam(name="todoDesc", required = true, defaultValue = "null") String todoDesc,@RequestParam(name="todoPerson", required = true, defaultValue = "null") String todoPerson, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
        // getTodos().set(Integer.parseInt(todoId), todoDesc);
        getTodos().get(todoId).setDesc(todoDesc);
        getTodos().get(todoId).setPerson(todoPerson);
        // mapping zu todos, es spart einen umweg, man geht direkt zur todoseite, es muss nicht alles nochmal mit einer tabelle gemacht werden

        return "redirect:/todos";
    }
    
    @RequestMapping("/addtodo")
    public String addtodo(@RequestParam(name="todoDesc", required = true, defaultValue = "null") String todoDesc,@RequestParam(name="todoPerson", required = true, defaultValue = "null") String todoPerson, @RequestParam(name="activePage", required = false, defaultValue = "todos") String activePage, Model model){
       
        getTodos().add(new Todo(todoDesc, todoPerson));
        return "redirect:/todos";
        // return "redirect:/modal";
        // if(todoDesc == null){
        //     return "redirect:/todo_Allert";
        // }
    }
    
    public void setTodos(ArrayList<Todo> todos) {
        this.todos = todos;
    }

    public ArrayList<Todo> getTodos() {
        return todos;
    }
}
