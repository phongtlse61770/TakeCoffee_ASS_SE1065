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
using ActionFilterAttribute = System.Web.Http.Filters.ActionFilterAttribute;

namespace API.Security
{
    public class AuthenticationFilter : ActionFilterAttribute
    {
        public override void OnActionExecuting(HttpActionContext actionContext)
        {
            HttpRequestHeaders headers = actionContext.Request.Headers;

            String username = null;
            String password = null;
            bool isAllow = false;
            try
            {
                String action = actionContext.Request.RequestUri.Segments.Last();
                if (action.Equals("CheckLogin"))
                {
                    isAllow = true;
                }
                else
                {
                    username = headers.GetValues("username").First();
                    password = headers.GetValues("password").First();
                    using (UserHelper userHelper = new UserHelper())
                    {
                        isAllow = userHelper.Authenticate(username, password);
                    }
                }
                
            }
            catch (Exception)
            {
                //Do nothing   
            }
            
            if (!isAllow)
            {
                actionContext.Response = new HttpResponseMessage(HttpStatusCode.Forbidden);
            }
            base.OnActionExecuting(actionContext);
        }
    }
}