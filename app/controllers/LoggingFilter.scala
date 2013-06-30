import play.api.Logger
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

object LoggingFilter extends Filter {
  def apply(next: RequestHeader => Result)(rh: RequestHeader) = {
    val start = System.currentTimeMillis

    def logTime(result: PlainResult): Result = {
      val time = System.currentTimeMillis - start

      Logger.info(s"${rh.method} ${rh.uri} took ${time}ms and returned ${result.header.status}")
      result.withHeaders("Request-Time" -> time.toString)
    }

    next(rh) match {
      case plain: PlainResult => logTime(plain)
      case async: AsyncResult => async.transform(logTime)
    }

  }
}