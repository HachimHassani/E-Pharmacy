import com.grp10.e_pharmacy.controller.MedicamentResource;
import com.grp10.e_pharmacy.model.MedicamentDTO;
import com.grp10.e_pharmacy.service.MedicamentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest(MedicamentResource.class)
@ContextConfiguration(classes = {MedicamentResource.class, MedicamentService.class})
public class MedicamentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicamentService medicamentService;

    @InjectMocks
    private MedicamentResource medicamentResource;

    @Test
    public void getAllMedicaments_ReturnsOk() throws Exception {
        List<MedicamentDTO> medicaments = new ArrayList<>();
        // add some sample data to medicaments list

        when(medicamentService.findAll()).thenReturn(medicaments);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/medicaments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // You can add more assertions based on your application's behavior
    }

    @Test
    public void getMedicamentById_ReturnsOk() throws Exception {
        Long medicamentId = 1L;
        MedicamentDTO medicament = new MedicamentDTO();
        // set properties for medicament

        when(medicamentService.get(medicamentId)).thenReturn(medicament);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/medicaments/{id}", medicamentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // You can add more assertions based on your application's behavior
    }
}
