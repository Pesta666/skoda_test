package cz.pesta.skoda_test.ui.preview

import cz.pesta.skoda_test.data.model.Battery
import cz.pesta.skoda_test.data.model.BodyType
import cz.pesta.skoda_test.data.model.Car
import cz.pesta.skoda_test.data.model.Engine
import cz.pesta.skoda_test.data.model.Render
import cz.pesta.skoda_test.data.model.ViewPoint

val previewCars = listOf(
    Car(
        vin = "DMBGF9NY3NF032963",
        name = "Enyaq RS",
        model = "Škoda Enyaq Coupé RS iV",
        modelYear = "2022",
        body = BodyType.HATCHBACK,
        trimLevel = "RS",
        battery = Battery(
            capacityInKWh = 82
        ),
        engine = Engine(
            type = "iV",
            powerInKW = 150
        ),
        renders = listOf(
            Render(
                url = "https://livemspstorage.blob.core.windows.net/demomode/enyaq_rs_coupe_green_main.png",
                viewPoint = ViewPoint.MAIN
            ),
            Render(
                url = "https://livemspstorage.blob.core.windows.net/demomode/enyaq_rs_coupe_green_garage_l.png",
                viewPoint = ViewPoint.GARAGE_L
            )
        )
    ),
    Car(
        vin = "DMBAN9NP4M7006008",
        name = "Superb",
        model = "Škoda Superb",
        modelYear = "2021",
        body = BodyType.LIFTBACK,
        trimLevel = "Style",
        battery = null,
        engine = Engine(
            type = "TSI",
            powerInKW = 110,
            capacityInLiters = 1.5
        ),
        renders = listOf(
            Render(
                url = "https://livemspstorage.blob.core.windows.net/demomode/superb_ice_white_main.png",
                viewPoint = ViewPoint.MAIN
            ),
            Render(
                url = "https://livemspstorage.blob.core.windows.net/demomode/superb_ice_white_garage_l.png",
                viewPoint = ViewPoint.GARAGE_L
            )
        )
    ),
    Car(
        vin = "DMBAN9NP4M7006333",
        name = "Fabia",
        model = "Škoda Fabia",
        modelYear = "2020",
        body = BodyType.HATCHBACK,
        trimLevel = "Style",
        battery = null,
        engine = Engine(
            type = "TSI",
            powerInKW = 81,
            capacityInLiters = 1.0
        ),
        renders = listOf(
            Render(
                url = "https://livemspstorage.blob.core.windows.net/demomode/fabia_red_main.png",
                viewPoint = ViewPoint.MAIN
            ),
            Render(
                url = "https://livemspstorage.blob.core.windows.net/demomode/fabia_red_garage_l.png",
                viewPoint = ViewPoint.GARAGE_L
            )
        )
    )
)