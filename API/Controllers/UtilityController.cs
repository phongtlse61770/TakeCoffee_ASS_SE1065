using System;
using System.Web.Http;

namespace API.Controllers
{
    [RoutePrefix("utility")]
    public class UtilityController : ApiController
    {
        [HttpPost]
        [Route("shipfee")]
        public IHttpActionResult GetShipFee()
        {
            return NotFound();
        }
    }
}