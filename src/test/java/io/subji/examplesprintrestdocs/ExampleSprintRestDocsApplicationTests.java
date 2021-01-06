package io.subji.examplesprintrestdocs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest(classes = ExampleSprintRestDocsApplication.class)
class ExampleSprintRestDocsApplicationTests {

	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setUp(
		WebApplicationContext webApplicationContext, 
		RestDocumentationContextProvider restDocumentationContextProvider)	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
			.apply(documentationConfiguration(restDocumentationContextProvider))
			.alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
			.build();
	}

	@Test
	public void readExample() throws Exception {
		this.mockMvc.perform(get("/test"))
		.andExpect(status().isOk())
		.andDo(
			document("test-example", 
				preprocessRequest(prettyPrint()), 
				preprocessResponse(prettyPrint()), 
				PayloadDocumentation.responseFields(
					PayloadDocumentation.fieldWithPath("id")
						.description("ID 필드 입니다."), 
					PayloadDocumentation.fieldWithPath("name")
						.description("이름 필드 입니다."), 
					PayloadDocumentation.fieldWithPath("body")
						.description("데이터 필드 입니다.")
		)));
			// links(
			// 	// linkWithRel 은 텍스트를 링크화 한다. 
			// 	linkWithRel("test").description("The Test resource")), 
			// 		//subsectionWithPath 를 사용해서 메서드에 대한 응답을 문서화 했다.
			// 		responseFields(subsectionWithPath("_links")
			// 			.description("Links to other resources")),
			// 		// 응답에 "Content-type" 헤더를 추가하기 위해 headerWithName 을 사용
			// 		responseHeaders(headerWithName("Content-Type")
			// 			.description("The Content-Type of the payload, e.g. `application/hal+json`")))
	}

}
