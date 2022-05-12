package lotto.view;

import static lotto.constants.LottoConstants.*;
import static lotto.constants.LottoGuideMessage.*;
import static lotto.constants.LottoGuideMessage.DIVIDE_LINE;
import static lotto.constants.LottoGuideMessage.PURCHASE_COUNT;
import static lotto.constants.LottoGuideMessage.TOTAL_PROFIT_RATE;
import static lotto.constants.LottoGuideMessage.WINNING_STATISTICS;
import static lotto.constants.LottoGuideMessage.WINNING_STATISTICS_GUIDE;

import java.util.Map;
import java.util.Map.Entry;
import lotto.constants.LottoConstants;
import lotto.constants.LottoGuideMessage;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.WinningRecord;
import lotto.domain.WinningStatistics;
import lotto.enums.WinningRank;

public class LottoResultView {

    public void printLottos(Lottos lottos) {
        System.out.printf((PURCHASE_COUNT) + "%n", lottos.getCount());
        for (Lotto lotto : lottos.getReadOnlyLottos()){
            System.out.println(lotto.getLottoNumbers());
        }
        newLine();
    }

    public void printWinningStatistics(WinningStatistics winningStatistics) {
        newLine();
        System.out.println(WINNING_STATISTICS_GUIDE);
        System.out.println(DIVIDE_LINE);
        printWinningRecord(winningStatistics.getWinningRecord());
        printTotalProfitRate(winningStatistics.getTotalProfitRate());
    }

    private void printTotalProfitRate(double totalProfitRate) {
        System.out.printf((TOTAL_PROFIT_RATE) + "%n", totalProfitRate, printProfitRateAdditionalDescription(totalProfitRate));
    }

    private String printProfitRateAdditionalDescription(double totalProfitRate) {
        if (Double.compare(totalProfitRate, PROFIT_CRITERIA) == 0) {
            return String.format(TOTAL_PROFIT_RATE_ADDITIONAL_DESCRIPTION, PROFIT_NO_CHANGE);
        }

        return Double.compare(totalProfitRate, PROFIT_CRITERIA) > 0 ?
            String.format(TOTAL_PROFIT_RATE_ADDITIONAL_DESCRIPTION, PROFIT) :
            String.format(TOTAL_PROFIT_RATE_ADDITIONAL_DESCRIPTION, PROFIT_LOSS);
    }

    private void printWinningRecord(WinningRecord winningRecord) {
        Map<WinningRank, Integer> record = winningRecord.getRecord();
        for (Entry<WinningRank, Integer> entry : record.entrySet()) {
            System.out.printf((WINNING_STATISTICS) + "%n",
                    entry.getKey().getWinningCount(),
                    entry.getKey().getPrizeMoney(),
                    entry.getValue());
        }
    }

    private void newLine() {
        System.out.println();
    }
}
