package org.mkhackathon.routing.graphhopper;

import com.graphhopper.PathWrapper;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import org.mkhackathon.routing.Step;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StepConverter {

    static final BigDecimal MILES_IN_METRES = BigDecimal.valueOf(0.000621371);

    static List<Step> convert(InstructionList instructions) {
        return StreamSupport.stream(instructions.spliterator(), false)
                .map(StepConverter::convert)
                .collect(Collectors.toList());
    }

    static Step convert(Instruction instruction) {
        return Step.builder()
                .withInstruction(instruction.getName())
                .withDistanceFromStart(convertDistanceFromMetresToMiles(instruction))
                .withTimeFromStart(convertTimeFromMillisToMeters(instruction))
                .build();
    }

    private static int convertTimeFromMillisToMeters(Instruction instruction) {
        double minutes = instruction.getTime() / 1000 / 60;
        if (minutes >= 1) {
            return (int) Math.round(minutes);
        }
        return 1;
    }

    private static double convertDistanceFromMetresToMiles(Instruction instruction) {
        return BigDecimal.valueOf(instruction.getDistance())
                .multiply(MILES_IN_METRES)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
    }

}
