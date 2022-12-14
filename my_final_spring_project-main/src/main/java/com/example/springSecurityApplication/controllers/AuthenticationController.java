package com.example.springSecurityApplication.controllers;

import com.example.springSecurityApplication.models.Person;
import com.example.springSecurityApplication.repositories.ProductRepository;
import com.example.springSecurityApplication.services.PersonService;
import com.example.springSecurityApplication.services.ProductService;
import com.example.springSecurityApplication.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {
    private final PersonValidator personValidator;
    private final PersonService personService;

    @Autowired
    public AuthenticationController(PersonValidator personValidator, PersonService personService) {
        this.personValidator = personValidator;
        this.personService = personService;
    }

    @GetMapping("/login")
    public String login(){
        return "authentication/login";
    }

//    @GetMapping("/registration")
//    public String registration(Model model){
//        model.addAttribute("person", new Person());
//
//    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("person") Person person){
        return "authentication/registration";

    }

    @PostMapping("/registration")
    public String resultRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        System.out.println("Метод сохранения");
        personValidator.validate(person, bindingResult); // Если валидатор возвращает ошибку помещаем данную ошибку в bindingResult
        if(bindingResult.hasErrors()){
            return "authentication/registration";
        }
        personService.register(person);
        return "redirect:/index";
    }

    @Controller
    public static class ProductController {
        public String products() {
            return "product/product";
        }
    }
//
//    @Controller
//    @RequestMapping("/product")
//    public class ProductController {
//
//        private final ProductRepository productRepository;
//        private final ProductService productService;
//
//        @Autowired
//        public ProductController(ProductRepository productRepository, ProductService productService) {
//            this.productRepository = productRepository;
//            this.productService = productService;
//        }
//
//        @GetMapping("")
//        public String getAllProduct(Model model) {
//            model.addAttribute("products", productService.getAllProduct());
//            return "/product/product";
//        }
//
//        @GetMapping("/info/{id}")
//        public String infoUser(@PathVariable("id") int id, Model model){
//            model.addAttribute("product", productService.getProductId(id));
//            return "/product/infoProduct";
//        }
//
//        @PostMapping("/search")
//        public String productSearch(@RequestParam("search") String search,
//                                    @RequestParam("ot") String ot,
//                                    @RequestParam("do") String Do,
//                                    @RequestParam(value = "price", required = false, defaultValue = "") String price,
//                                    @RequestParam(value = "contact", required = false, defaultValue = "")String contact,
//                                    Model model){
//            if(!ot.isEmpty() & !Do.isEmpty()){
//                if(!price.isEmpty()){
//                    if(price.equals("sorted_by_ascending_price")){
//                        if(!contact.isEmpty())
//                        {
//                            if(contact.equals("furniture")){
//                                model.addAttribute("search_product",
//                                        productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
//                                                Float.parseFloat(ot),
//                                                Float.parseFloat(Do),
//                                                1));
//                            } else if(contact.equals("appliances")){
//                                model.addAttribute("search_product",
//                                        productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
//                                                Float.parseFloat(ot),
//                                                Float.parseFloat(Do),
//                                                2));
//                            }else if(contact.equals("clothes")){
//                                model.addAttribute("search_product",
//                                        productRepository.findByTitleAndCategoryOrderByPriceAsc(search.toLowerCase(),
//                                                Float.parseFloat(ot),
//                                                Float.parseFloat(Do),
//                                                3));
//                            }
//                        }
//                    }
//                    else if (price.equals("sorted_by_descending_price")){
//                        if(!contact.isEmpty())
//                        {
//                            if(contact.equals("furniture")){
//                                model.addAttribute("search_product",
//                                        productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
//                                                Float.parseFloat(ot),
//                                                Float.parseFloat(Do),
//                                                1));
//                            } else if(contact.equals("appliances")){
//                                model.addAttribute("search_product",
//                                        productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
//                                                Float.parseFloat(ot),
//                                                Float.parseFloat(Do),
//                                                2));
//                            }else if(contact.equals("clothes")){
//                                model.addAttribute("search_product",
//                                        productRepository.findByTitleAndCategoryOrderByPriceDesc(search.toLowerCase(),
//                                                Float.parseFloat(ot),
//                                                Float.parseFloat(Do),
//                                                3));
//                            }
//                        }
//                    }
//                } else {
//                    model.addAttribute("search_product",
//                            productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(search,
//                                    Float.parseFloat(ot),
//                                    Float.parseFloat(Do)));
//
//                }
//            }
//            else {
//                model.addAttribute("search_product",
//                        productRepository.findByTitleContainingIgnoreCase(search));
//            }
//
//            model.addAttribute("value_search", search);
//            model.addAttribute("value_price_ot", ot);
//            model.addAttribute("value_price_do", Do);
//            model.addAttribute("products", productService.getAllProduct());
//            return "/product/product";
//
//        }
//
//    }
}
//http:localhost:8080/authentication/login


