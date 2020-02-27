package com.github.vmas.beermeter.core

import com.github.vmas.beermeter.core.model.Beer
import java.math.BigDecimal

/**
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
class BeerVolatileStore : BeerStore {

    private val store = mutableMapOf<String, Beer>()

    init {
        addOrUpdateBeer(
            Beer(
                name = "Warka",
                imgUrl = "https://grupazywiec.pl/wp-content/uploads/2016/03/2Warka_classic_butelka_packshot_2015_cream-1.png",
                type = "Lager",
                percentage = BigDecimal.valueOf(55, 1),
                country = "polska",
                website = "https://grupazywiec.pl/marki/warka/"
            )
        )
        addOrUpdateBeer(
            Beer(
                name = "Tyskie",
                imgUrl = "https://www.kp.pl/uploads/kp/beers2/Tyskie_gronie.png",
                type = "Lager",
                percentage = BigDecimal.valueOf(52, 1),
                country = "Polska",
                website = "https://www.tyskie.pl/piwa"
            )
        )
        addOrUpdateBeer(
            Beer(
                name = "Żywiec",
                imgUrl = "https://grupazywiec.pl/wp-content/uploads/2016/02/Zywiec-Lager-butelka-500-ml-1.png",
                type = "Lager",
                percentage = BigDecimal.valueOf(56, 1),
                country = "Polska",
                website = "https://grupazywiec.pl/marki/zywiec/"
            )
        )
        addOrUpdateBeer(
            Beer(
                name = "EB",
                imgUrl = "https://grupazywiec.pl/wp-content/uploads/2016/03/EB_butelka_500_prosta-1.png",
                type = "Lager",
                percentage = BigDecimal.valueOf(52, 1),
                country = "Polska",
                website = "https://grupazywiec.pl/marki/eb/"
            )
        )
        addOrUpdateBeer(
            Beer(
                name = "Heineken",
                imgUrl = "https://www.heineken.com/pl/~/resources/Heineken/Poland/H4Poland/header_heineken.png",
                type = "Jasny Lager",
                percentage = BigDecimal.valueOf(50, 1),
                country = "Holandia",
                website = "https://www.heineken.com/pl/we-are-heineken/our-beer"
            )
        )
        addOrUpdateBeer(
            Beer(
                name = "Carlsberg",
                imgUrl = "https://carlsbergpolska.pl/media/6151/carlsberg_profil.png",
                type = "Lager",
                percentage = BigDecimal.valueOf(50, 1),
                country = "Dania",
                website = "https://carlsbergpolska.pl/products/carlsberg/carlsberg/?Ckey=13776"
            )
        )
        addOrUpdateBeer(
            Beer(
                name = "Guinness Draught",
                imgUrl = "https://carlsbergpolska.pl/media/6174/guinness_44_cl.png",
                type = "Irish Stout",
                percentage = BigDecimal.valueOf(42, 1),
                country = "Irlandia",
                website = "https://carlsbergpolska.pl/products/guinness/guinness-draught/?Ckey=13776"
            )
        )
    }

    override fun addOrUpdateBeer(beer: Beer, name: String) {
        if (name != beer.name) {
            removeBeer(name)
        }
        store[beer.name] = beer
    }

    override fun removeBeer(name: String) {
        store.remove(name)
    }

    override fun getAll(): List<Beer> {
        return store.values.toList()
    }

    override fun getBeer(name: String): Beer? {
        return store[name]
    }

    override fun beerExists(name: String): Boolean {
        return store.containsKey(name)
    }
}
