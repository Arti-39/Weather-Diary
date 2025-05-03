package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {
    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation(value = "일기 생성", notes = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장")
    @PostMapping("/create/diary")
    public void crateDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "생성할 날짜", example = "2025-01-01")
            LocalDate date,
            @RequestBody String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation(value = "일기 불러오기", notes = "선택한 날짜의 모든 일기 데이터를 가져오기")
    @GetMapping("/read/diary")
    public List<Diary> readDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회할 날짜", example = "2025-01-01")
            LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation(value = "여러 일기 불러오기", notes = "선택한 기간중의 모든 일기 데이터를 가져오기")
    @GetMapping("/read/diaries")
    public List<Diary> readDiaries(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회할 기간의 첫번째 날", example = "2025-01-01")
            LocalDate startDate,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "조회할 기간의 마지막 날", example = "2025-01-31")
            LocalDate endDate) {
        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation(value = "일기 수정", notes = "선택한 날짜의 첫번째 일기를 수정하기")
    @PutMapping("/update/diary")
    public void updateDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "수정할 날짜", example = "2025-01-01")
            LocalDate date,
            @RequestBody String text) {
        diaryService.updateDiary(date, text);
    }

    @ApiOperation(value = "일기 삭제", notes = "선택한 날짜의 모든 일기를 삭제하기")
    @DeleteMapping("/delete/diary")
    public void deleteDiary(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @ApiParam(value = "삭제할 날짜", example = "2025-01-01")
            LocalDate date) {
        diaryService.deleteDiary(date);
    }
}
