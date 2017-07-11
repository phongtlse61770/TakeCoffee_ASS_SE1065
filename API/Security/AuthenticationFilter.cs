using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Security.Claims;
using System.Threading;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http.Controllers;
using System.Web.Http.Filters;
using System.Web.Http.Results;
using System.Web.Mvc;
using System.Web.Mvc.Filters;
using Entity.Helper;
using Newtonsoft.Json.Linq;
using ActionFilterAttribute = System.Web.Http.Filters.ActionFilterAttribute;

namespace API.Security
{
    public class AuthenticationFilter : ActionFilterAttribute
    {
        public override void OnActionExecuting(HttpActionContext actionContext)
        {
            HttpRequestHeaders headers = actionContext.Request.Headers;

            bool isAllow = false;
            String action = actionContext.Request.RequestUri.Segments.Last();
            switch (action.ToLower())
            {
                case "checklogin":
                case "checkloginemployee":
                case "create":
                    isAllow = true;
                    break;
                default:
                    try
                    {
                        string username = headers.GetValues("username").First();
                        string password = headers.GetValues("password").First();
                        using (UserHelper userHelper = new UserHelper())
                        {
                            isAllow = userHelper.Authenticate(username, password);
                        }
                    }
                    catch (Exception)
                    {
                    }


                    break;
            }


            if (!isAllow)
            {
                JObject rep = new JObject
                {
                    ["message"] = "Access denied"
                };
                actionContext.Response =
                    new HttpResponseMessage
                    {
                        StatusCode = HttpStatusCode.Forbidden,
                        Content = new StringContent(rep.ToString())
                    };
            }
            base.OnActionExecuting(actionContext);
        }
    }
}