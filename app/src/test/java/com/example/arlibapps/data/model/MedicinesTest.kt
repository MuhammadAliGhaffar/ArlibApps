import com.example.arlibapps.data.model.Medicine
import org.junit.Assert.assertEquals
import org.junit.Test

class MedicineTest {

    @Test
    fun `Medicine instances with the same properties should be equal`() {
        val medicine1 = Medicine(id = 1, name = "Medicine1", benefits = "Benefits1", diseaseCure = "Cure1", dosageMg = 100)
        val medicine2 = Medicine(id = 1, name = "Medicine1", benefits = "Benefits1", diseaseCure = "Cure1", dosageMg = 100)

        assertEquals(medicine1, medicine2)
    }

    @Test
    fun `Medicine instances with different properties should not be equal`() {
        val medicine1 = Medicine(id = 1, name = "Medicine1", benefits = "Benefits1", diseaseCure = "Cure1", dosageMg = 100)
        val medicine2 = Medicine(id = 2, name = "Medicine2", benefits = "Benefits2", diseaseCure = "Cure2", dosageMg = 200)

        assert(medicine1 != medicine2)
    }
}