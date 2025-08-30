package cz.pesta.skoda_test.data.persistence.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cz.pesta.skoda_test.data.model.BodyType
import cz.pesta.skoda_test.data.model.Render

class RenderListConverter {
    @TypeConverter
    fun fromString(value: String?): List<Render> {
        if (value.isNullOrBlank()) return emptyList()
        val listType = object : TypeToken<List<Render>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Render>?): String {
        return Gson().toJson(list ?: emptyList<Render>())
    }
}

class BodyTypeConverter {
    @TypeConverter
    fun fromBodyType(body: BodyType?): String? = body?.name

    @TypeConverter
    fun toBodyType(value: String?): BodyType? =
        value?.let { enumValueOf<BodyType>(it) }
}