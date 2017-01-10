package persistence

import slick.driver.MySQLDriver.api._
import slick.lifted.{ ProvenShape, ForeignKeyQuery }
import domain._

/**
  * Created by locnguyen on 1/11/17.
  */
class Categories(tag: Tag)
  extends Table[Category](tag, "SUPPLIERS") {

  def id: Rep[Int] = column[Int]("CAT_ID", O.PrimaryKey)
  def name: Rep[String] = column[String]("CAT_NAME")

  def * = (id, name) <> (Category.tupled, Category.unapply)

}

class Products(tag: Tag)
  extends Table[Product](tag, "PRODUCTS") {

  def id: Rep[Int] = column[Int]("PRO_ID", O.PrimaryKey)
  def name: Rep[String] = column[String]("PRO_NAME")
  def catId: Rep[Int] = column[Int]("CAT_ID")
  def price: Rep[Double] = column[Double]("PRICE")
  def stock: Rep[Int] = column[Int]("STOCK")

  def * = (id, name, catId, price, stock) <> (Product.tupled, Product.unapply)
}
