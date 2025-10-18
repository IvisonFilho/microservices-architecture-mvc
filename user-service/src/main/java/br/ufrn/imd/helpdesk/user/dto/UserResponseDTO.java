package br.ufrn.imd.helpdesk.user.dto;

public record UserResponseDTO(
    Long id,
    String name,
    String email
) {
}
