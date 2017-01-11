package org.mkhackathon.routing.graphhopper;

import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import org.junit.Before;
import org.junit.Test;
import org.mkhackathon.routing.Step;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepConverterTest {

    private InstructionList instructions;

    @Before
    public void setUp() throws Exception {
        instructions = InstructionList.EMPTY;
    }

    @Test
    public void should_convert_list_of_instructions_to_points() throws Exception {
        instructions.add(instruction("Continue", 46.825, 9364));

        List<Step> steps = StepConverter.convert(instructions);

        assertThat(steps.size(), is(1));
        assertThat(steps.get(0).getInstruction(), equalTo("Continue"));
        assertThat(steps.get(0).getInstruction(), equalTo("Continue"));
        assertThat(steps.get(0).getDistance(), equalTo(0.03));
        assertThat(steps.get(0).getTime(), equalTo(1));


    }

    private Instruction instruction(String desc, double distance, int time) {
        Instruction instruction = new Instruction(Instruction.CONTINUE_ON_STREET, "Continue", null, null);
        instruction.setDistance(distance);
        instruction.setTime(time);
        return instruction;
    }
}