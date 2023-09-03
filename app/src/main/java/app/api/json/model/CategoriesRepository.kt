package app.api.json.model

class CategoriesRepository: ItemsRepository {

    override suspend fun fetch(): Items {
        return arrayListOf(
            Item("Category 1", "", "https://via.placeholder.com/150/92c952"),
            Item("Category 2", "", "https://via.placeholder.com/150/771796"),
            Item("Category 3", "", "https://via.placeholder.com/150/771796"),
            Item("Category 4", "", "https://via.placeholder.com/150/d32776"),
            Item("Category 5", "", "https://via.placeholder.com/150/f66b97"),
        )
    }

}