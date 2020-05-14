package android.example.shop.domain.interactor

import android.example.shop.domain.RemoteCategory
import android.example.shop.domain.RemoteProduct
import android.example.shop.domain.dao.CategoryDao
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val RemoteCategoryDao: CategoryDao
) {
    //suspend operator fun invoke() = RemoteCategoryDao.getCategories()
    operator fun invoke(): List<RemoteCategory> {
        return mutableListOf(
            RemoteCategory(
                "Ван Гог",
                mutableListOf(
                    RemoteProduct(
                        id = "1",
                        name = "Звездная ночь",
                        price = 2000000.0,
                        discountPercent = 10,
                        description = "Одна из наиболее известных картин нидерландского художника-постимпрессиониста Винсента Ван Гога",
                        imageUrl = "https://cdn.britannica.com/78/43678-050-F4DC8D93/Starry-Night-canvas-Vincent-van-Gogh-New-1889.jpg",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1889"
                            )
                        )
                    ),
                    RemoteProduct(
                        id = "2",
                        name = "Пшеничное поле с кипарисами",
                        price = 2000000.0,
                        discountPercent = 5,
                        description = "«Пшеничное поле с кипарисами» — это название трех работ художника, которые очень похожи по своему содержанию",
                        imageUrl = "https://collectionapi.metmuseum.org/api/collection/v1/iiif/436535/796067/main-image",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1889"
                            )
                        )
                    ),
                    RemoteProduct(
                        id = "3",
                        name = "Едоки картофеля",
                        price = 1000020.0,
                        discountPercent = 15,
                        description = "Первая крупная работа автора. В ней он хотел изобразить крестьян настолько реалистично, насколько это возможно. Мастер сознательно выбрал в качестве натурщиков простых и грубых людей, которые, кажется, приступили к трапезе, не помыв руки после возделывания земли",
                        imageUrl = "https://artrue.ru/wp-content/uploads/2016/05/potato-eaters.jpg",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1885"
                            )
                        )
                    ),
                    RemoteProduct(
                        id = "4",
                        name = "Спальня в Арле",
                        price = 3000000.0,
                        discountPercent = 20,
                        description = "Как и в случае с пшеничными полями, картина «Спальня в Арле» существует в трех различных версиях. Они различаются фотографиями на правой стене. Миниатюрные картины изображают автопортрет художника и его друзей",
                        imageUrl = "https://artrue.ru/wp-content/uploads/2017/01/Spalnya_v_Arle-1024x812.jpg",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1888"
                            )
                        )
                    ),
                    RemoteProduct(
                        id = "5",
                        name = "Автопортрет с перевязанным ухом",
                        price = 200000.0,
                        discountPercent = 10,
                        description = "Данный портрет изображает художника после инцидента с другим известным мастером, Полем Гогеном, в результате которого Винсент отрезал часть своего уха бритвой, а затем раненный живописец отправился в бордель. ",
                        imageUrl = "https://artrue.ru/wp-content/uploads/2017/01/Avtoportret_s_otrezanim_uhom-836x1024.jpg",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1889"
                            )
                        )
                    )
                )
            ),
            RemoteCategory(
                name = "Пабло Пикассо",
                products = mutableListOf(
                    RemoteProduct(
                        id = "6",
                        name = "Арлекин с бокалом",
                        price = 200000.0,
                        discountPercent = 15,
                        description = "Эта картина, передающая атмосферу богемного Парижа начала 20 века, была заказана Пикассо хозяином кабаре Лапин Агиль (Проворный кролик) на Монмартре, и провисела там в течение нескольких лет. Хозяин кабаре изображен на заднем плане картины, с гитарой, а в образе Арлекина у барной стойки Пикассо изобразил самого себя",
                        imageUrl = "http://picassolive.ru/wp-content/uploads/2013/02/Pablo-Picasso_Au-Lapin-Agile_1905.jpg",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1905"
                            )
                        )
                    ),
                    RemoteProduct(
                        id = "7",
                        name = "Гертруда Стайн",
                        price = 300000.0,
                        discountPercent = 20,
                        description = "Она приходила позировать в мастерскую Пикассо в общежитие Бато-Лавуар десятки раз, портрет не давался, Пикассо непрерывно переписывал голову, сначала в профиль, потом анфас, потом в три четверти. Наконец, он воскликнул - Всё, я больше уже не вижу вас! - и отставив работу, уехал с Фернандой в путешествие в Испанию. Вернувшись, он закончил работу",
                        imageUrl = "http://picassolive.ru/wp-content/uploads/2012/07/Pablo-Picasso_Portrait-de-Gertrude-Stein_1905-1906.jpg",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1905-1906"
                            ),
                            RemoteProduct.Attribute(
                                name = "Техника",
                                value = "Холст, масло"
                            )
                        )
                    ),
                    RemoteProduct(
                        id = "8",
                        name = "Автопортрет",
                        price = 3000000.0,
                        discountPercent = 15,
                        description = "Автопортрет написан в тот же год, что и картина Авиньонские девицы, и напоминает эскизы к ней. Этот период, для которого характерны огрубление и примитивизация формы, носит название сезанновского",
                        imageUrl = "http://picassolive.ru/wp-content/uploads/2013/06/Pablo-Picasso_Autoportrait_1907.jpg",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1907"
                            )
                        )
                    ),
                    RemoteProduct(
                        id = "9",
                        name = "Купальщицы",
                        price = 4000000.0,
                        discountPercent = 12,
                        description = "С 1917 года круг общения Пикассо расширяется, кроме парижской богемы он вращается в театральной среде, оформляя балет Парад для труппы Дягилева",
                        imageUrl = "http://picassolive.ru/wp-content/uploads/2011/12/Pablo-Picasso_Les-baigneuses_19182.jpg",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1918"
                            )
                        )
                    ),
                    RemoteProduct(
                        id = "10",
                        name = "Портрет Ольги в кресле",
                        price = 4500000.0,
                        discountPercent = 15,
                        description = "Пикассо попадает в театральную среду, он оформляет спектакли для балета труппы Дягилева, влюбляется в русскую балерину Ольгу Хохлову, в следующем году они поженятся. Ольга хочет, чтобы на портретах она была похожа, она склонна к традиционности, консерватизму и респектабельности",
                        imageUrl = "http://picassolive.ru/wp-content/uploads/2016/04/Pablo-Picasso-Portrait-d-Olga-dans-un-fauteuil-1917.jpg",
                        attributes = mutableListOf(
                            RemoteProduct.Attribute(
                                name = "Год создания",
                                value = "1917"
                            )
                        )
                    )
                )
            ),
            RemoteCategory(
                name = "Иван Айвазовский",
                products = mutableListOf()
            ),
            RemoteCategory(
                name = "Иван Шишкин",
                products = mutableListOf()
            ),
            RemoteCategory(
                name = "Каземир Малевич",
                products = mutableListOf()
            ),
            RemoteCategory(
                name = "Константин Коровин",
                products = mutableListOf()
            ),
            RemoteCategory(
                name = "Карл Брюллов",
                products = mutableListOf()
            ),
            RemoteCategory(
                name = "Исаак Левитан",
                products = mutableListOf()
            ),
            RemoteCategory(
                name = "Микелянджело",
                products = mutableListOf()
            ),
            RemoteCategory(
                name = "",
                products = mutableListOf()
            )
        )
    }
}