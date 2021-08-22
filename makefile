
SRC_DIR := src

OUT_DIR := out

SRCS := $(wildcard $(SRC_DIR)/*.java)

CLS := $(SRCS:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

JC := javac

JCFLAGS := -d $(OUT_DIR)/ -cp $(SRC_DIR)/

.SUFFIXES: .java

.PHONY: all clean

all: $(CLS)

$(CLS): $(OUT_DIR)/%.class: $(SRC_DIR)/%.java $(JC) $(JCFLAGS) $<

clean: rm $(OUT_DIR)/*.class

V1 = sampleInput100000.txt
V2 = 7
V3 = out.txt
run: java Main.java ${V1} ${V2} ${V3}