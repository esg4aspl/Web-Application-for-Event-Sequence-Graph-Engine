package controllers;


import java.util.Set;

import models.Book;
import play.mvc.Controller;
import play.mvc.Result;

import play.libs.Json;
public class BooksController extends Controller {
	
	public Result index()//loga yazdırıp console da goruntuleyebilirsin
	{
		Set<Book> books=Book.allBooks();
		//return ok(Json.toJson(books));
		return ok("asd");
	}

	public Result create()
	{
		return TODO;
	}
	
	public Result save()
	{
		return TODO;
	}

	public Result edit(Integer id)
	{
		return TODO;
	}

	public Result update()
	{
		return TODO;
	}

	public Result destroy(Integer id)
	{
		return TODO;
	}

	//for book details

	public Result show(Integer id)
	{
		return TODO;
	}
	
	
}
