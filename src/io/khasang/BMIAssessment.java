package io.khasang;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BMIAssessment {

    private final Map<Integer, String> bmiMap;

    public BMIAssessment(Properties properties) {
        this.bmiMap = properties.entrySet().stream()
                .collect(Collectors.toMap(
                        k -> convertBmi(Double.parseDouble(k.getKey().toString())),
                        v -> String.valueOf(v.getValue()),
                        (o1, o2) -> o1, TreeMap::new));
    }

    public String bmiInterpretation(double bmi) {
        final Integer bmiInt = convertBmi(bmi);
        for (Integer bmiKey : bmiMap.keySet()) {
            if (bmiKey >= bmiInt) return bmiMap.get(bmiKey);
        }
        return "ERROR";
    }

    private Integer convertBmi(double bmi) {
        return (int) bmi * 10;
    }

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put(7.0, "Выраженный дефицит массы тела");
        prop.put(14.0, "Недостаточная (дефицит) масса тела");
        prop.put(60.0, "Норма");
        prop.put(70.0, "Избыточная масса тела (предожирение)");
        prop.put(75.0, "Ожирение");
        prop.put(80.0, "Ожирение резкое");
        prop.put(100.0, "Очень резкое ожирение");

        BMIAssessment BMIAssessment = new BMIAssessment(prop);
        System.out.println(BMIAssessment.bmiInterpretation(71));
    }
}