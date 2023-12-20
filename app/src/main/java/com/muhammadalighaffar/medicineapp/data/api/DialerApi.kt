package com.muhammadalighaffar.medicineapp.data.api

import com.muhammadalighaffar.medicineapp.data.model.Medicines
import retrofit2.Response
import retrofit2.http.GET

const val BASE_URL = "https://run.mocky.io/"
const val URL_MEDICINE = "v3/e5423b88-1a23-44e4-a692-9618ab87e802"
interface DialerApi {

    @GET(URL_MEDICINE)
    suspend fun getMedicines(): Response<Medicines>
}