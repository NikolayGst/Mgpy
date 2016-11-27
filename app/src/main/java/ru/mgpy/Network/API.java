package ru.mgpy.Network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.mgpy.Model.Lesson;

public interface API {

    @FormUrlEncoded
    @POST("mdpu/lesson.php")
    Call<Lesson> getLessonList(@Field("group") String group);

}
