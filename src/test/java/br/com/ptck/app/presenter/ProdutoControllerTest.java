package br.com.ptck.app.presenter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.ptck.app.TestGenerator;
import br.com.ptck.app.core.Produto;
import br.com.ptck.app.core.usecase.CreateProdutoUseCase;
import br.com.ptck.app.core.usecase.UpdateProdutoUseCase;
import br.com.ptck.app.presenter.rest.entities.ProdutoRequest;
import br.com.ptck.app.presenter.rest.entities.ProdutoResponse;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @MockBean
    CreateProdutoUseCase createProdutoUseCase;

    @MockBean
    UpdateProdutoUseCase updateProdutoUseCase;

    @Autowired
    MockMvc mockMvc;

    @Test
    void createProdutoReturnCreated() throws Exception {
        Produto expected = TestGenerator.randomProduto();
        ProdutoRequest request = ProdutoRequest.from(expected);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(expected);

        CreateProdutoUseCase.OutputValues output = new CreateProdutoUseCase.OutputValues(
                ProdutoResponse.from(expected));

        doReturn(output)
                .when(createProdutoUseCase)
                .execute(any());

        mockMvc.perform(post("/produto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));


    }

    @Test
    void updateProdutoReturnCreated() throws Exception {
        Produto expected = TestGenerator.randomProduto();
        ProdutoRequest request = ProdutoRequest.from(expected);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(expected);

        UpdateProdutoUseCase.OutputValues output = new UpdateProdutoUseCase.OutputValues(
                ProdutoResponse.from(expected));

        doReturn(output)
                .when(updateProdutoUseCase)
                .execute(any());

        mockMvc.perform(put("/produto/{id}", expected.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

}
