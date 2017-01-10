package repositories

import com.redis.RedisClient
import domain.Cart

/**
  * Created by locnguyen on 1/11/17.
  */
trait CartRepository {

  def listCarts: List[Cart]

  def insertCart(user: Cart): Cart

  def updateCart(user: Cart)

  def findById(id: Int): Cart

  def deleteCart(id: Int)

}

object CartRepositoryImpl extends CartRepository {

  val client = new RedisClient("localhost", 6379)

  val carts = client.hgetall("carts").get

  val idSequence = carts.size

  override def listCarts: List[Cart] = {
    carts.map { (key) => (Cart(key._1.toInt, key._2.toInt)) } toList
  }

  override def insertCart(cart: Cart): Cart = {
    val newId = idSequence + 1
    val createdCart = cart.copy(id = newId.toInt)

    client.hset("carts", newId, cart.productId)

    createdCart
  }

  override def updateCart(cart: Cart) {
    client.hset("carts", cart.id, cart.productId)
  }

  override def findById(id: Int): Cart = {
    Cart(id, client.hget("carts", id).get.toInt)
  }

  override def deleteCart(id: Int) {
    client.hdel("carts", id)
  }

}