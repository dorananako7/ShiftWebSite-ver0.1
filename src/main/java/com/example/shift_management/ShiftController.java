package com.example.shift_management;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model; //htmlにデータを運ぶための箱
import org.springframework.web.bind.annotation.PostMapping;//POSTリクエストを受け取りため
import org.springframework.web.bind.annotation.RequestParam; //URLのパラ、エータをメソッドの変数に渡すため
import java.util.*;
import org.springframework.web.bind.annotation.RequestBody;

@Controller // webリクエストの処理を行うクラス
public class ShiftController {
  private final DbRepository repository;
  private final List<String> DATES = List.of("9/24 (水)", "9/25 (木)", "9/26 (金)", "9/27 (土)", "9/28 (日)", "9/29 (月)",
      "9/30 (火)");
  private final List<String> TIMES = List.of("9:00 - 10:00", "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00",
      "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00");

  // SpringBootが自動でDbRepositoryを渡してくれるコンストラクタ
  public ShiftController(DbRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/")
  public String index(Model model) {
    List<DbEntity> allshifts = repository.findAll(); // データベースから全データを取得
    List<List<List<DbEntity>>> shiftTable = new ArrayList();
    for (String time : TIMES) {
      List<List<DbEntity>> tate = new ArrayList();
      for (String date : DATES) {
        List<DbEntity> yoko = new ArrayList();
        for (DbEntity shift : allshifts) {
          if (time.equals(shift.getShiftTime()) && date.equals(shift.getShiftDate())) {
            yoko.add(shift);
          }
        }
        tate.add(yoko);
      }
      shiftTable.add(tate);
    }

    model.addAttribute("dates", DATES);
    model.addAttribute("times", TIMES);
    model.addAttribute("shiftTable", shiftTable);

    return "index"; // templates/index.htmlを返す
  }

  @PostMapping("/shifts/add")
  public String addShift(@RequestParam String personName,
      @RequestParam String shiftDate,
      @RequestParam String shiftTime) {
    DbEntity newShift = new DbEntity();
    newShift.setPersonName(personName);
    newShift.setShiftDate(shiftDate);
    newShift.setShiftTime(shiftTime);
    repository.save(newShift); // データベースに保存
    return "redirect:/"; // 処理後にトップページにリダイレクト
  }

  @GetMapping("/shifts/{id}")
  // @PathVariableはURLの{id}の部分をlong型で引数として取得するためのアノテーション
  public String showShiftInfo(@PathVariable Long id, Model model) {
    DbEntity shift = repository.findById(id).get();
    if (shift != null) {
      model.addAttribute("shift", shift);
      return "delete";
    } else {
      return "redirect:/";
    }
  }

  @PostMapping("/shifts/delete")
  public String deleteShift(@RequestParam Long id) {
    repository.deleteById(id);
    return "redirect:/"; // 処理後にトップページにリダイレクト"
  }

  @GetMapping("/pingz")
  public ResponseEntity<String> healthCheck() {
    return ResponseEntity.ok("OK");
  }

}
