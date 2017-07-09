using System.IO;
using System.Web;
using System.Web.Mvc;

namespace Admin.Controllers
{
    public class TestController : Controller
    {
        
        public ActionResult Index()
        {
            return
            View();
        }

        [HttpPost]
        public ActionResult Index(HttpPostedFileBase file)
        {
            // Verify that the user selected a file
            if (file != null && file.ContentLength > 0) 
            {
                // extract only the filename
                var fileName = Path.GetFileName(file.FileName);
                // store the file inside ~/App_Data/uploads folder
                if (fileName != null)
                {
                    var path = Path.Combine(Server.MapPath("~/App_Data/images"), fileName);
                    file.SaveAs(path);
                }
            }
            // redirect back to the index action to show the form once again
            return RedirectToAction("Index");   
        }
    }
}