package uz.pdp.appcodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String text;

    private String solution;

    private String example;

    @ManyToOne(optional = false)
    private TaskCategory taskCategory;

    public Task(String name, String text, String solution, String example, TaskCategory taskCategory) {
        this.name = name;
        this.text = text;
        this.solution = solution;
        this.example = example;
        this.taskCategory = taskCategory;
    }
}
