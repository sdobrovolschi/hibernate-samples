package com.sdobrovolschi.hibernate.benchmark;

import com.sdobrovolschi.hibernate.Application;
import com.sdobrovolschi.hibernate.cartesianproduct.application.ClientService;
import com.sdobrovolschi.hibernate.cartesianproduct.domain.model.Client;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Stanislav Dobrovolschi
 */
@Warmup(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class DirtyCheckBenchmark {

    // <include file="db.data.xml" relativeToChangelogFile="true"/>
    // disable ProxyTestDataSourceBeanPostProcessor
    // disable p6spy
    // disable hibernate.generate_statistics
    // disable logging
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DirtyCheckBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @Measurement(iterations = 10)
    public void fetchReadOnlyTransaction(BenchmarkContext context, Blackhole blackhole) {
        List<Client> clients = context.clientService.getAll();
        blackhole.consume(clients);
    }

    @State(Scope.Benchmark)
    public static class BenchmarkContext {

        ApplicationContext context;
        ClientService clientService;

        @Setup
        public void setUp() {
            context = SpringApplication.run(Application.class);
            clientService = context.getBean(ClientService.class);
        }
    }
}
