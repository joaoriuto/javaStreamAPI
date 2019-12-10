package br.com.treinaweb.java.streams;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		List<Empregado> empregados = new ArrayList<>();
		
		empregados.add(new Empregado(1, "João", 2000, "Producao"));
		empregados.add(new Empregado(2, "Maria", 3000, "RH"));
		empregados.add(new Empregado(3, "José", 5000, "Controladoria"));
		empregados.add(new Empregado(4, "Joasefina", 7000, "CTO"));
		
		System.out.println(" ** LISTA DE EMPREGADOS **");
		/*
		for (Empregado emp : empregados) {
			System.out.println(emp.getNome());
		}
		*/
		
		/* Stream API */
		
		empregados.stream().forEach(emp -> {
			System.out.println(emp.getNome());
		});
		
		/*
		double salarioTotal = 0;
		
		for (Empregado emp : empregados) {
			salarioTotal = salarioTotal + emp.getSalario();
		}*/
		
		double salarioTotal = empregados.stream().mapToDouble(emp -> emp.getSalario()).sum();
		
		System.out.println("Salário Total: R$ " + salarioTotal);
		
		Mensageiro mensageiro = (mensagem) -> 
			System.out.println("Mensagem lambda: " + mensagem);
	
			mensageiro.emitirMensagem("treinaWeb");
			
			System.out.println("** Filter dos funcionários com J");
			Stream<Empregado> streamEmpregados = empregados.stream();
			Stream<Empregado> empregadosComJ = streamEmpregados.filter(emp -> emp.getNome().startsWith("J"));
			empregadosComJ.forEach((emp) -> System.out.println(emp.getNome()));
			
			DoubleSummaryStatistics sumario = empregados.stream().collect(Collectors.summarizingDouble(Empregado :: getSalario));
			System.out.println(" ** ESTATISTICAS TOPEERS **");
			System.out.println("Maior Salario: R$: " + sumario.getMax());
			System.out.println("Menor Salario: R$: " + sumario.getMin());
			System.out.println("Media Salarial: R$: " + sumario.getAverage());
			System.out.println("Soma de todos os Salario: R$: " + sumario.getSum());
	}
	
	
}
