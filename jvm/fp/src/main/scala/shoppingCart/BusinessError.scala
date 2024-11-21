package shoppingCart

import scala.util.control.NoStackTrace

enum BusinessError extends NoStackTrace:
  case EmptyCartError
  case PaymentError(message: String)
  case OrderError(message: String)
