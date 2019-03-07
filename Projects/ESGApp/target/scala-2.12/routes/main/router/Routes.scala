// @GENERATOR:play-routes-compiler
// @SOURCE:/home/burcu/git/Web-Application-for-Event-Sequence-Graph-Engine/Projects/ESGApp/conf/routes
// @DATE:Wed Mar 06 20:07:21 EET 2019

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_2: controllers.HomeController,
  // @LINE:8
  CountController_1: controllers.CountController,
  // @LINE:10
  AsyncController_4: controllers.AsyncController,
  // @LINE:12
  PersonController_0: controllers.PersonController,
  // @LINE:19
  ESGController_5: controllers.ESGController,
  // @LINE:32
  BooksController_3: controllers.BooksController,
  // @LINE:41
  Assets_6: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_2: controllers.HomeController,
    // @LINE:8
    CountController_1: controllers.CountController,
    // @LINE:10
    AsyncController_4: controllers.AsyncController,
    // @LINE:12
    PersonController_0: controllers.PersonController,
    // @LINE:19
    ESGController_5: controllers.ESGController,
    // @LINE:32
    BooksController_3: controllers.BooksController,
    // @LINE:41
    Assets_6: controllers.Assets
  ) = this(errorHandler, HomeController_2, CountController_1, AsyncController_4, PersonController_0, ESGController_5, BooksController_3, Assets_6, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_2, CountController_1, AsyncController_4, PersonController_0, ESGController_5, BooksController_3, Assets_6, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """count""", """controllers.CountController.count"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """message""", """controllers.AsyncController.message"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sayHello""", """controllers.PersonController.sayHello()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sayHello""", """controllers.PersonController.sayHello()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sayHelloWithTextPlain""", """controllers.PersonController.sayHelloWithTestPlain()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sayHelloWithBodyParser""", """controllers.PersonController.sayHelloWithBodyParser()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sayHelloWithBodyParser""", """controllers.PersonController.sayHelloWithBodyParser()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """welcome/""" + "$" + """name<[^/]+>/""" + "$" + """lastName<[^/]+>""", """controllers.HomeController.welcomeName(name:String, lastName:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """saveESGToDB""", """controllers.ESGController.saveESGToDB()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """sendJson""", """controllers.ESGController.saveESGToDB()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """saveJPEG""", """controllers.ESGController.saveJPEGToDB()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """readData""", """controllers.ESGController.readData()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books""", """controllers.BooksController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/create""", """controllers.BooksController.create()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/""" + "$" + """id<[^/]+>""", """controllers.BooksController.show(id:Integer)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/edit/""" + "$" + """id<[^/]+>""", """controllers.BooksController.edit(id:Integer)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/edit""", """controllers.BooksController.update()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/create""", """controllers.BooksController.save()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """books/delete/""" + "$" + """id<[^/]+>""", """controllers.BooksController.destroy(id:Integer)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_2.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_CountController_count1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("count")))
  )
  private[this] lazy val controllers_CountController_count1_invoker = createInvoker(
    CountController_1.count,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CountController",
      "count",
      Nil,
      "GET",
      this.prefix + """count""",
      """ An example controller showing how to use dependency injection""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_AsyncController_message2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("message")))
  )
  private[this] lazy val controllers_AsyncController_message2_invoker = createInvoker(
    AsyncController_4.message,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AsyncController",
      "message",
      Nil,
      "GET",
      this.prefix + """message""",
      """ An example controller showing how to write asynchronous code""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_PersonController_sayHello3_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sayHello")))
  )
  private[this] lazy val controllers_PersonController_sayHello3_invoker = createInvoker(
    PersonController_0.sayHello(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PersonController",
      "sayHello",
      Nil,
      "POST",
      this.prefix + """sayHello""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_PersonController_sayHello4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sayHello")))
  )
  private[this] lazy val controllers_PersonController_sayHello4_invoker = createInvoker(
    PersonController_0.sayHello(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PersonController",
      "sayHello",
      Nil,
      "GET",
      this.prefix + """sayHello""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_PersonController_sayHelloWithTestPlain5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sayHelloWithTextPlain")))
  )
  private[this] lazy val controllers_PersonController_sayHelloWithTestPlain5_invoker = createInvoker(
    PersonController_0.sayHelloWithTestPlain(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PersonController",
      "sayHelloWithTestPlain",
      Nil,
      "POST",
      this.prefix + """sayHelloWithTextPlain""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_PersonController_sayHelloWithBodyParser6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sayHelloWithBodyParser")))
  )
  private[this] lazy val controllers_PersonController_sayHelloWithBodyParser6_invoker = createInvoker(
    PersonController_0.sayHelloWithBodyParser(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PersonController",
      "sayHelloWithBodyParser",
      Nil,
      "POST",
      this.prefix + """sayHelloWithBodyParser""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_PersonController_sayHelloWithBodyParser7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sayHelloWithBodyParser")))
  )
  private[this] lazy val controllers_PersonController_sayHelloWithBodyParser7_invoker = createInvoker(
    PersonController_0.sayHelloWithBodyParser(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PersonController",
      "sayHelloWithBodyParser",
      Nil,
      "GET",
      this.prefix + """sayHelloWithBodyParser""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_HomeController_welcomeName8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("welcome/"), DynamicPart("name", """[^/]+""",true), StaticPart("/"), DynamicPart("lastName", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_welcomeName8_invoker = createInvoker(
    HomeController_2.welcomeName(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "welcomeName",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """welcome/""" + "$" + """name<[^/]+>/""" + "$" + """lastName<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_ESGController_saveESGToDB9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("saveESGToDB")))
  )
  private[this] lazy val controllers_ESGController_saveESGToDB9_invoker = createInvoker(
    ESGController_5.saveESGToDB(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ESGController",
      "saveESGToDB",
      Nil,
      "POST",
      this.prefix + """saveESGToDB""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_ESGController_saveESGToDB10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("sendJson")))
  )
  private[this] lazy val controllers_ESGController_saveESGToDB10_invoker = createInvoker(
    ESGController_5.saveESGToDB(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ESGController",
      "saveESGToDB",
      Nil,
      "POST",
      this.prefix + """sendJson""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_ESGController_saveJPEGToDB11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("saveJPEG")))
  )
  private[this] lazy val controllers_ESGController_saveJPEGToDB11_invoker = createInvoker(
    ESGController_5.saveJPEGToDB(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ESGController",
      "saveJPEGToDB",
      Nil,
      "GET",
      this.prefix + """saveJPEG""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_ESGController_readData12_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("readData")))
  )
  private[this] lazy val controllers_ESGController_readData12_invoker = createInvoker(
    ESGController_5.readData(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ESGController",
      "readData",
      Nil,
      "POST",
      this.prefix + """readData""",
      """""",
      Seq()
    )
  )

  // @LINE:32
  private[this] lazy val controllers_BooksController_index13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books")))
  )
  private[this] lazy val controllers_BooksController_index13_invoker = createInvoker(
    BooksController_3.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "index",
      Nil,
      "GET",
      this.prefix + """books""",
      """""",
      Seq()
    )
  )

  // @LINE:33
  private[this] lazy val controllers_BooksController_create14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/create")))
  )
  private[this] lazy val controllers_BooksController_create14_invoker = createInvoker(
    BooksController_3.create(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "create",
      Nil,
      "GET",
      this.prefix + """books/create""",
      """""",
      Seq()
    )
  )

  // @LINE:34
  private[this] lazy val controllers_BooksController_show15_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BooksController_show15_invoker = createInvoker(
    BooksController_3.show(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "show",
      Seq(classOf[Integer]),
      "GET",
      this.prefix + """books/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:35
  private[this] lazy val controllers_BooksController_edit16_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/edit/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BooksController_edit16_invoker = createInvoker(
    BooksController_3.edit(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "edit",
      Seq(classOf[Integer]),
      "GET",
      this.prefix + """books/edit/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:36
  private[this] lazy val controllers_BooksController_update17_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/edit")))
  )
  private[this] lazy val controllers_BooksController_update17_invoker = createInvoker(
    BooksController_3.update(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "update",
      Nil,
      "POST",
      this.prefix + """books/edit""",
      """""",
      Seq()
    )
  )

  // @LINE:37
  private[this] lazy val controllers_BooksController_save18_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/create")))
  )
  private[this] lazy val controllers_BooksController_save18_invoker = createInvoker(
    BooksController_3.save(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "save",
      Nil,
      "POST",
      this.prefix + """books/create""",
      """""",
      Seq()
    )
  )

  // @LINE:38
  private[this] lazy val controllers_BooksController_destroy19_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("books/delete/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_BooksController_destroy19_invoker = createInvoker(
    BooksController_3.destroy(fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BooksController",
      "destroy",
      Seq(classOf[Integer]),
      "GET",
      this.prefix + """books/delete/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:41
  private[this] lazy val controllers_Assets_versioned20_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned20_invoker = createInvoker(
    Assets_6.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_2.index)
      }
  
    // @LINE:8
    case controllers_CountController_count1_route(params@_) =>
      call { 
        controllers_CountController_count1_invoker.call(CountController_1.count)
      }
  
    // @LINE:10
    case controllers_AsyncController_message2_route(params@_) =>
      call { 
        controllers_AsyncController_message2_invoker.call(AsyncController_4.message)
      }
  
    // @LINE:12
    case controllers_PersonController_sayHello3_route(params@_) =>
      call { 
        controllers_PersonController_sayHello3_invoker.call(PersonController_0.sayHello())
      }
  
    // @LINE:13
    case controllers_PersonController_sayHello4_route(params@_) =>
      call { 
        controllers_PersonController_sayHello4_invoker.call(PersonController_0.sayHello())
      }
  
    // @LINE:14
    case controllers_PersonController_sayHelloWithTestPlain5_route(params@_) =>
      call { 
        controllers_PersonController_sayHelloWithTestPlain5_invoker.call(PersonController_0.sayHelloWithTestPlain())
      }
  
    // @LINE:15
    case controllers_PersonController_sayHelloWithBodyParser6_route(params@_) =>
      call { 
        controllers_PersonController_sayHelloWithBodyParser6_invoker.call(PersonController_0.sayHelloWithBodyParser())
      }
  
    // @LINE:16
    case controllers_PersonController_sayHelloWithBodyParser7_route(params@_) =>
      call { 
        controllers_PersonController_sayHelloWithBodyParser7_invoker.call(PersonController_0.sayHelloWithBodyParser())
      }
  
    // @LINE:17
    case controllers_HomeController_welcomeName8_route(params@_) =>
      call(params.fromPath[String]("name", None), params.fromPath[String]("lastName", None)) { (name, lastName) =>
        controllers_HomeController_welcomeName8_invoker.call(HomeController_2.welcomeName(name, lastName))
      }
  
    // @LINE:19
    case controllers_ESGController_saveESGToDB9_route(params@_) =>
      call { 
        controllers_ESGController_saveESGToDB9_invoker.call(ESGController_5.saveESGToDB())
      }
  
    // @LINE:20
    case controllers_ESGController_saveESGToDB10_route(params@_) =>
      call { 
        controllers_ESGController_saveESGToDB10_invoker.call(ESGController_5.saveESGToDB())
      }
  
    // @LINE:21
    case controllers_ESGController_saveJPEGToDB11_route(params@_) =>
      call { 
        controllers_ESGController_saveJPEGToDB11_invoker.call(ESGController_5.saveJPEGToDB())
      }
  
    // @LINE:22
    case controllers_ESGController_readData12_route(params@_) =>
      call { 
        controllers_ESGController_readData12_invoker.call(ESGController_5.readData())
      }
  
    // @LINE:32
    case controllers_BooksController_index13_route(params@_) =>
      call { 
        controllers_BooksController_index13_invoker.call(BooksController_3.index)
      }
  
    // @LINE:33
    case controllers_BooksController_create14_route(params@_) =>
      call { 
        controllers_BooksController_create14_invoker.call(BooksController_3.create())
      }
  
    // @LINE:34
    case controllers_BooksController_show15_route(params@_) =>
      call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_BooksController_show15_invoker.call(BooksController_3.show(id))
      }
  
    // @LINE:35
    case controllers_BooksController_edit16_route(params@_) =>
      call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_BooksController_edit16_invoker.call(BooksController_3.edit(id))
      }
  
    // @LINE:36
    case controllers_BooksController_update17_route(params@_) =>
      call { 
        controllers_BooksController_update17_invoker.call(BooksController_3.update())
      }
  
    // @LINE:37
    case controllers_BooksController_save18_route(params@_) =>
      call { 
        controllers_BooksController_save18_invoker.call(BooksController_3.save())
      }
  
    // @LINE:38
    case controllers_BooksController_destroy19_route(params@_) =>
      call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_BooksController_destroy19_invoker.call(BooksController_3.destroy(id))
      }
  
    // @LINE:41
    case controllers_Assets_versioned20_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned20_invoker.call(Assets_6.versioned(path, file))
      }
  }
}
