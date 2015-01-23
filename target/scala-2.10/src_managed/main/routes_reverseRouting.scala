// @SOURCE:/Users/chad.cuddigan/dev/simple-rest-scala/conf/routes
// @HASH:5236ed99017a250a62cff2bd7a2c2bbee5828232
// @DATE:Thu Jan 22 13:16:28 PST 2015

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString


// @LINE:10
// @LINE:7
// @LINE:6
package controllers {

// @LINE:10
class ReverseAssets {


// @LINE:10
def versioned(file:String): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:6
def listBooks(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "books")
}
                        

// @LINE:7
def saveBook(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "books")
}
                        

}
                          
}
                  


// @LINE:10
// @LINE:7
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:10
class ReverseAssets {


// @LINE:10
def versioned : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.versioned",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:6
def listBooks : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.listBooks",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "books"})
      }
   """
)
                        

// @LINE:7
def saveBook : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.saveBook",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "books"})
      }
   """
)
                        

}
              
}
        


// @LINE:10
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:10
class ReverseAssets {


// @LINE:10
def versioned(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.versioned(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "versioned", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

}
                          

// @LINE:7
// @LINE:6
class ReverseApplication {


// @LINE:6
def listBooks(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.listBooks(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "listBooks", Seq(), "GET", """ Home page""", _prefix + """books""")
)
                      

// @LINE:7
def saveBook(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.saveBook(), HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "saveBook", Seq(), "POST", """""", _prefix + """books""")
)
                      

}
                          
}
        
    