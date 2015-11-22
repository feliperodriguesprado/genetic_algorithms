package br.edu.univas.ga.production;

import br.edu.univas.ga.framework.GAController;
import br.edu.univas.ga.framework.GAModel;

public class ProdutionApp {

    public static void main(String[] args) {

        ProductionModel model = new ProductionModel();
        model.setGenerationQuantity(10000);
        model.setPopulationSize(120);
        model.setElitism(true);
        model.setSelectionType(GAModel.SelectionType.CLASSIFICATION);
        model.setCrossType(GAModel.CrossType.PERMUTATION);
        model.setForeignIndividualRate(0.3f);
        model.setMutationType(GAModel.MutationType.PERMUTATION);
        model.setMutationRate(0.05f);
        model.setMutationQuantity(1);

        GAController controller = new GAController(model);
        controller.execute();

    }

}
