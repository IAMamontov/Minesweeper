fun Fridge.take(productName: String): Product {
    this.open()
    val pM = this.find(productName)
    this.close()
    return pM
}