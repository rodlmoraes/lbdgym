package com.gym.lbdgym.controller.room;

import java.util.Optional;

import com.gym.lbdgym.model.room.SquashRoom;
import com.gym.lbdgym.service.room.SquashRoomService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({ "/squashRoom" })
@RequiredArgsConstructor
public class SquashRoomController {
  private final SquashRoomService service;

  @GetMapping(path = { "/{id}" })
  public ResponseEntity<SquashRoom> findById(@PathVariable Long id) {
    Optional<SquashRoom> squashRoom = service.findById(id);
    if (!squashRoom.isPresent()) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(squashRoom.get());
  }

  @PostMapping
  public ResponseEntity<SquashRoom> create(@RequestBody SquashRoom squashRoom) {
    return ResponseEntity.ok(service.save(squashRoom));
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<SquashRoom> update(@PathVariable("id") Long id, @RequestBody SquashRoom SquashRoom) {
    if (!service.findById(id).isPresent()) {
      return ResponseEntity.badRequest().build();
    }
    return ResponseEntity.ok(service.save(SquashRoom));
  }

  @DeleteMapping(path = { "/{id}" })
  public ResponseEntity<?> delete(@PathVariable Long id) {
    if (!service.findById(id).isPresent()) {
      return ResponseEntity.badRequest().build();
    }
    service.deleteById(id);
    return ResponseEntity.ok().build();
  }
}