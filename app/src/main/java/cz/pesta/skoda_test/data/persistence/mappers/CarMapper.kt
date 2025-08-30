package cz.pesta.skoda_test.data.persistence.mappers

import cz.pesta.skoda_test.data.model.Battery
import cz.pesta.skoda_test.data.model.Car
import cz.pesta.skoda_test.data.model.Engine
import cz.pesta.skoda_test.data.persistence.entity.BatteryEntity
import cz.pesta.skoda_test.data.persistence.entity.CarEntity
import cz.pesta.skoda_test.data.persistence.entity.EngineEntity

fun transform(carEntity: CarEntity): Car {
    return Car(
        vin = carEntity.vin,
        name = carEntity.name,
        model = carEntity.model,
        modelYear = carEntity.modelYear,
        body = carEntity.body,
        trimLevel = carEntity.trimLevel,
        battery = transform(carEntity.battery),
        engine = transform(carEntity.engine),
        renders = carEntity.renders
    )
}

fun transform(car: Car): CarEntity {
    return CarEntity(
        vin = car.vin,
        name = car.name,
        model = car.model,
        modelYear = car.modelYear,
        body = car.body,
        trimLevel = car.trimLevel,
        battery = transform(car.battery),
        engine = transform(car.engine),
        renders = car.renders
    )
}

fun transform(batteryEntity: BatteryEntity?): Battery? {
    if (batteryEntity == null) return null
    return Battery(
        capacityInKWh = batteryEntity.capacityInKWh
    )
}

fun transform(battery: Battery?): BatteryEntity? {
    if (battery == null) return null
    return BatteryEntity(
        capacityInKWh = battery.capacityInKWh
    )
}

fun transform(engineEntity: EngineEntity): Engine {
    return Engine(
        type = engineEntity.type,
        powerInKW = engineEntity.powerInKW,
        capacityInLiters = engineEntity.capacityInLiters
    )
}

fun transform(engine: Engine): EngineEntity {
    return EngineEntity(
        type = engine.type,
        powerInKW = engine.powerInKW,
        capacityInLiters = engine.capacityInLiters
    )
}