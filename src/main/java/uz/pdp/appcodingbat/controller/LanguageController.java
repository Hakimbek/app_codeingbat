package uz.pdp.appcodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.payload.LanguageDto;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {
    @Autowired
    LanguageService languageService;


    /**
     * GET LANGUAGES LIST
     *
     * @return LANGUAGES LIST
     */
    @GetMapping
    public ResponseEntity<List<Language>> get() {
        List<Language> languages = languageService.get();
        return ResponseEntity.ok(languages);
    }


    /**
     * GET ONE LANGUAGE BY ID
     *
     * @param id INTEGER
     * @return ONE LANGUAGE
     */
    @GetMapping("/{id}")
    public ResponseEntity<Language> getById(@PathVariable Integer id) {
        Language language = languageService.getById(id);
        return ResponseEntity.ok(language);
    }


    /**
     * DELETE LANGUAGE BY ID
     *
     * @param id INTEGER
     * @return RESULT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Result> delete(@PathVariable Integer id) {
        Result result = languageService.delete(id);
        return ResponseEntity.status(result.isSuccess() ? 200 : 405).body(result);
    }


    /**
     * ADD LANGUAGE
     *
     * @param languageDto NAME (String)
     * @return RESULT
     */
    @PostMapping
    public ResponseEntity<Result> add(@RequestBody LanguageDto languageDto) {
        Result result = languageService.add(languageDto);
        return ResponseEntity.status(result.isSuccess() ? 201 : 409).body(result);
    }


    /**
     * EDIT LANGUAGE BY ID
     *
     * @param id          INTEGER
     * @param languageDto NAME (String)
     * @return RESULT
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> edit(@PathVariable Integer id, @RequestBody LanguageDto languageDto) {
        Result result = languageService.edit(id, languageDto);
        return ResponseEntity.status(result.isSuccess() ? 202 : 409).body(result);
    }
}
