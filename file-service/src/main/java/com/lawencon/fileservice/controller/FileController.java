package com.lawencon.fileservice.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.core.dto.file.FileDataDto;
import com.lawencon.core.dto.file.FileInsertReqDto;
import com.lawencon.core.dto.response.DataResDto;
import com.lawencon.core.dto.response.DeleteResDto;
import com.lawencon.core.dto.response.InsertResDto;
import com.lawencon.core.dto.response.TransactionResDto;
import com.lawencon.fileservice.service.declaration.FileService;

@RestController
@RequestMapping("files")
public class FileController {
    
    @Autowired
	private FileService fileService;
    
    private final LocalDateTime now = LocalDateTime.now();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	
	@PreAuthorize("hasAnyAuthority('RLEMP', 'RLSYS')")
	@PostMapping
	public ResponseEntity<TransactionResDto<InsertResDto>> insert(@RequestBody @Valid final FileInsertReqDto data){
		final TransactionResDto<InsertResDto> result = fileService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("download")
	public ResponseEntity<?> getById(@RequestParam("id") final String id){
		final String formatted = now.format(formatter);
        final DataResDto<FileDataDto> file = fileService.getById(id);
        final byte[] fileBytes = Base64.getDecoder().decode(file.getData().getEncode());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=attachment."+formatted+"." + file.getData().getExtension())
                .body(fileBytes);
	}
	
	@PreAuthorize("hasAnyAuthority('RLEMP', 'RLSYS')")
	@DeleteMapping
	public ResponseEntity<TransactionResDto<DeleteResDto>> delete(@RequestParam("id") final String id) {
		final TransactionResDto<DeleteResDto> result = fileService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
