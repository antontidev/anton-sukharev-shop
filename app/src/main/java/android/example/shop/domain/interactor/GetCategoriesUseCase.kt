package android.example.shop.domain.interactor

import android.example.shop.domain.RemoteCategory
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.CategoryDao
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryDao: CategoryDao
) {
    //suspend operator fun invoke() = categoryDao.getCategories()
    suspend operator fun invoke(): List<RemoteCategory> {
        return listOf(
            RemoteCategory(
                "Кошки",
                listOf(
                    RemoteProduct(
                        id = "1",
                        name = "Антон",
                        price = 20.0,
                        discountPercent = 0,
                        description = "",
                        imageUrl = "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/other/cat_relaxing_on_patio_other/1800x1200_cat_relaxing_on_patio_other.jpg?resize=750px:*",
                        attributes = listOf()
                    ),
                    RemoteProduct(
                        id = "1",
                        name = "Беляш",
                        price = 20.0,
                        discountPercent = 0,
                        description = "",
                        imageUrl = "https://media2.24aul.ru/imgs/5e21da55346dbc0001614556/",
                        attributes = listOf()
                    ),
                    RemoteProduct(
                        id = "1",
                        name = "Мурка",
                        price = 20.0,
                        discountPercent = 0,
                        description = "",
                        imageUrl = "https://2.bp.blogspot.com/-Cy-fB6lD75s/UKyyHcKFJtI/AAAAAAAABes/Xn98YSFCLlw/s1600/lilbub1.jpg",
                        attributes = listOf()
                    )
                )
            ),
            RemoteCategory(
                "Собаки",
                listOf(
                    RemoteProduct(
                        id = "1",
                        name = "Юля",
                        price = 20.0,
                        discountPercent = 0,
                        description = "",
                        imageUrl = "https://post.healthline.com/wp-content/uploads/sites/3/2020/02/322868_1100-1100x628.jpg",
                        attributes = listOf()
                    ),
                    RemoteProduct(
                        id = "1",
                        name = "Наташа",
                        price = 20.0,
                        discountPercent = 0,
                        description = "",
                        imageUrl = "https://i.ytimg.com/vi/MPV2METPeJU/maxresdefault.jpg",
                        attributes = listOf()
                    ),
                    RemoteProduct(
                        id = "1",
                        name = "Сара",
                        price = 20.0,
                        discountPercent = 0,
                        description = "",
                        imageUrl = "https://www.nationalgeographic.com/content/dam/animals/thumbs/rights-exempt/mammals/d/domestic-dog_thumb.jpg",
                        attributes = listOf()
                    )
                )
            )
        )
    }
}