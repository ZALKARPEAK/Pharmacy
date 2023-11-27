package com.example.Trainee.Dto.Training.getTraineeTrainingsList;

import com.example.Trainee.entity.Training_Types;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TrainingResponse {
   private String trainingName;
   private Date trainingDate;
   private Training_Types trainingTypes;
   private Number duration;
   private String TrainerName;

   public TrainingResponse(String trainingName, Date trainingDate, String trainingTypeName, int duration, String username) {
   }
}
