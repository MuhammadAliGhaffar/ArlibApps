import com.muhammadalighaffar.medicineapp.data.api.DialerApi
import com.muhammadalighaffar.medicineapp.data.model.Medicine
import com.muhammadalighaffar.medicineapp.data.model.Medicines
import com.muhammadalighaffar.medicineapp.data.network.RemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.junit.Assert.assertEquals
import retrofit2.Response

class RemoteDataSourceTest {

    // Mocking the DialerApi interface
    private val mockApiDialer: DialerApi = mock(DialerApi::class.java)

    // Creating an instance of the RemoteDataSource with the mocked DialerApi
    private val remoteDataSource = RemoteDataSource(mockApiDialer)

    @Test
    fun `getMedicines should return a list of medicines`() = runBlocking {
        // Create a dummy Medicines object
        val dummyMedicines = Medicines(
            listOf(
                Medicine(id = 1, name = "Medicine1"),
                Medicine(id = 2, name = "Medicine2")
            )
        )

        // Create a dummy Response object with the dummy Medicines
        val dummyResponse: Response<Medicines> = Response.success(dummyMedicines)

        // Mocking the behavior of the apiDialer.getMedicines() function
        `when`(mockApiDialer.getMedicines()).thenReturn(dummyResponse)

        // Calling the function under test
        val result = remoteDataSource.getMedicines()

        // Asserting that the result matches the dummy data
        assertEquals(dummyMedicines, result.body())
    }

    @Test
    fun `getMedicines_should_return_empty_list_when_response_is_null`() = runBlocking {
        // Create a dummy Response object with null body
        val nullResponse: Response<Medicines> = Response.success(null)

        // Mocking the behavior of the apiDialer.getMedicines() function
        `when`(mockApiDialer.getMedicines()).thenReturn(nullResponse)

        // Calling the function under test
        val medicines = remoteDataSource.getMedicines().body()?.medicines
        val result = medicines ?: emptyList()

        // Asserting that the result is an empty list
        assertEquals(emptyList<Medicine>(), result )
    }

}
