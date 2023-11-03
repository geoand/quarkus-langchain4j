package io.quarkiverse.langchain4j.chroma.deployment;

import jakarta.inject.Singleton;

import org.jboss.jandex.DotName;

import io.quarkiverse.langchain4j.chroma.ChromaEmbeddingStore;
import io.quarkiverse.langchain4j.chroma.runtime.ChromaConfig;
import io.quarkiverse.langchain4j.chroma.runtime.ChromaRecorder;
import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class ChromaProcessor {

    public static final DotName CHROMA_EMBEDDING_STORE = DotName.createSimple(ChromaEmbeddingStore.class);

    static final String FEATURE = "langchain4j-chroma";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    public void createBean(
            BuildProducer<SyntheticBeanBuildItem> beanProducer,
            ChromaRecorder recorder,
            ChromaConfig config) {
        beanProducer.produce(SyntheticBeanBuildItem
                .configure(CHROMA_EMBEDDING_STORE)
                .defaultBean()
                .setRuntimeInit()
                .defaultBean()
                .scope(Singleton.class)
                .supplier(recorder.chromaStoreSupplier(config))
                .done());
    }
}
